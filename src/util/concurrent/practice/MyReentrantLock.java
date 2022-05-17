package util.concurrent.practice;

public class MyReentrantLock {

    Thread lockedBy;
    int counter;

    synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (lockedBy != null && callingThread != lockedBy)
            wait();

        if (lockedBy == null)
            lockedBy = callingThread;

            counter++;
    }


    synchronized void unlock(){
        Thread callingThread = Thread.currentThread();
        if (callingThread != lockedBy) throw new IllegalMonitorStateException("");
        counter--;
        if (counter == 0) {
            lockedBy = null;
            notify();
        }

    }
}
