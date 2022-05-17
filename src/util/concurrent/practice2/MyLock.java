package util.concurrent.practice2;

public class MyLock {

    Thread lockedBy;
    int count;

    synchronized void lock() throws InterruptedException {

        Thread currentThread = Thread.currentThread();

        while(lockedBy != null && currentThread != lockedBy) {
            wait();
        }

        lockedBy = currentThread;
        count++;

    }

    synchronized void unlock() {

        Thread currentThread = Thread.currentThread();
        if (lockedBy == null || lockedBy != currentThread) throw new IllegalMonitorStateException("");

        count--;
        if (count == 0) {
            lockedBy = null;
            notify();
        }

    }
}
