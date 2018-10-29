package util.concurrent.lock;

import java.util.LinkedList;
import java.util.Queue;

// THIS WOULD NOT  -- DON'T USE
public class LockWithFairness {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int counter = 0;
    private Queue<Thread> waitingThreads = new LinkedList<>();

    public synchronized void lock() throws InterruptedException{
        while (isLocked && lockedBy != Thread.currentThread()) {
            if (!waitingThreads.contains(Thread.currentThread()))
                waitingThreads.offer(Thread.currentThread());
            this.wait();
        }

        counter++;
        isLocked = true;
        lockedBy = Thread.currentThread();
    }

    public synchronized void unlock() throws InterruptedException {
        if (Thread.currentThread() != lockedBy)
            throw new IllegalMonitorStateException("The calling thread is not locked ");

        counter--;
        if (counter == 0) {
            isLocked = false;
            lockedBy = null;
            //waitingThreads.poll().notify();
        }
    }
}
