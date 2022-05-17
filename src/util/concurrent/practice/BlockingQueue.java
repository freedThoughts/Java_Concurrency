package util.concurrent.practice;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    Queue<Object> queue;
    int maxSize;

    BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList();
    }

    synchronized void enqueue(Object obj) throws InterruptedException {
        while (queue.size() == maxSize)
            wait();

        if (queue.size() == 0)
            notifyAll();

        queue.add(obj);

    }

    synchronized Object dequeue() throws InterruptedException {
        while (queue.size() == 0) wait();

        if (queue.size() == maxSize)
            notifyAll();

        return queue.poll();
    }
}
