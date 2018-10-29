package util.concurrent.lock;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLock {
    // Key is Thread having read access. Value is the number of read access hold by thread in key as per Re-entrance mechanism
    private Map<Thread, Integer> readers = new HashMap<>();
    // Only writer thread
    private Thread writer = null;
    // Number of writeRequests pending at present
    private int writeRequests = 0;
    // Number of write access hold by writer thread as per Re-entrance mechanism
    private int writeAccess = 0;

    public synchronized void lockRead() throws InterruptedException{
        while (!isReadAccessCanBeGranted())
            this.wait();

        // Counter represent the number of grants already provided of the calling Thread
        Integer counter = readers.get(Thread.currentThread());
        if (counter == null)
            readers.put(Thread.currentThread(), new Integer(1));
        else readers.put(Thread.currentThread(), ++counter);
    }

    public synchronized void unlockRead() {
        Integer counter = readers.get(Thread.currentThread());
        if (counter == null)
            throw new IllegalMonitorStateException("The calling thread is not readLocked");
        if (counter > 1)
            readers.put(Thread.currentThread(), --counter);
        else readers.remove(Thread.currentThread());
        this.notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException{
        writeRequests ++;
        while (!isWriteAccessCanBeGranted())
            this.wait();

        writeRequests--;
        writer = Thread.currentThread();
        writeAccess++;
    }

    public synchronized void unlockWrite() {
        if (writer != Thread.currentThread())
            throw new IllegalMonitorStateException("Calling thread don't have write access");
        writeAccess--;
        if (writeAccess == 0)
            writer = null;
        this.notifyAll();
    }

    private boolean isReadAccessCanBeGranted(){
        // If calling thread is already writing, we can grant read and write access together.
        if (Thread.currentThread() == writer) return true;
        // Some thread other than calling thread is writing, we can't grant read access
        if (writer != null) return false;
        // Calling thread is already reading. As per Re-entrance mechanism, we can grant read access
        if (readers.get(Thread.currentThread()) != null) return true;
        // A thread waiting for Write access, we would prioritize write over read. Read access can not be granted
        if (writeRequests > 0) return false;
        return true;
    }

    private boolean isWriteAccessCanBeGranted() {
        // The calling thread already reading and is the only reader. It may possibly be reader as well as writer.
        // If calling thread is only reader and don't have write access, we can grant read and write access together
        // If calling thread is only reader and also have write access, as per Re-entrance mechanism, we can grant write access
        if (readers.size() == 1 && readers.get(Thread.currentThread()) != null) return true;
        // Some other thread(s) reading currently, we can not grant write access
        if (readers.size() > 0) return false;
        // No thread is wring currently. We can grant write access
        if (writer == null) return true;
        // Some other thread is writing currently. We can not grant access
        if (writer != Thread.currentThread()) return false;
        return true;
    }

}
