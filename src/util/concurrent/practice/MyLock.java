package util.concurrent.practice;


public class MyLock {

    int lockCount;
    Thread callingThread;


    public synchronized void lock() throws InterruptedException {

        Thread currentThread = Thread.currentThread();
        if (callingThread == currentThread) lockCount++;
        if (callingThread == null) {
            callingThread = currentThread;
            lockCount++;
        }

        while (currentThread != callingThread ) wait();

    }

    public synchronized void unlock() {
        Thread currentThread = Thread.currentThread();
        if (callingThread == null || callingThread != currentThread) throw new IllegalArgumentException("");
        if (currentThread == callingThread && lockCount == 1) {
            callingThread = null;
            lockCount--;
            this.notify();
        } else
            lockCount--;


    }
}
