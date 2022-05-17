package util.concurrent.practice;

import java.util.HashSet;
import java.util.Set;

public class Semaphore {

    int permit;
    Set<Thread> permitAcquiredThreads;

    Semaphore(int permit) {
        this.permit = permit;
        this.permitAcquiredThreads = new HashSet<>();
    }

    synchronized boolean acquire() throws InterruptedException {
        if (permitAcquiredThreads.contains(Thread.currentThread())) return true;
        if (permitAcquiredThreads.size() < permit) {
            permitAcquiredThreads.add(Thread.currentThread());
            return true;
        }

        while (permitAcquiredThreads.size() == permit)
            wait();

        return true;
    }


    synchronized boolean release() {
        if(permitAcquiredThreads.contains(Thread.currentThread())) {
            permitAcquiredThreads.remove(Thread.currentThread());

            if (permitAcquiredThreads.size() < permit)
                notify();

            return true;
        }

        return false;
    }
}
