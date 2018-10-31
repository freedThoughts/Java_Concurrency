package util.concurrent.lock;

import java.util.LinkedList;
import java.util.Queue;

public class LockWithFairness {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private Queue<LockObject> waitingThreads = new LinkedList<LockObject>();

    public void lock() throws InterruptedException {
        LockObject lockObject = new LockObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waitingThreads.offer(lockObject);
        }

        while (isLockedForThisThread) {
            synchronized (this) {
                isLockedForThisThread = isLocked || waitingThreads.peek() != lockObject;
                if (!isLockedForThisThread) {
                    isLocked = true;
                    waitingThreads.remove(lockObject);
                    lockedBy = Thread.currentThread();
                } else
                    lockObject.lock();
            }
        }
    }

    public synchronized void unLock() throws InterruptedException {
        if (lockedBy != Thread.currentThread()) throw new IllegalMonitorStateException("The current Thread is not locked");
        isLocked = false;
        lockedBy = null;
        if (waitingThreads.size() > 0)
            waitingThreads.poll().unLock();
    }



    private static class LockObject {
        private boolean isLocked = false;
        private synchronized void lock() throws InterruptedException{
            while (isLocked)
                this.wait();
            this.isLocked = true;
        }

        private synchronized void unLock() throws InterruptedException {
            if (!isLocked) throw new IllegalMonitorStateException("LockObject is not locked");
            isLocked = false;
            this.notify();
        }
    }
}
