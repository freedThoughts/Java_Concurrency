package util.concurrent.blockingQueue;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {
    private Integer limit;
    private List<Object> list;

    BlockingQueue(Integer limit){
        this.limit = limit;
        list = new LinkedList<Object>();
    }

    synchronized void enqueue(Object object) throws InterruptedException {
        while(list.size() == limit)  // Thread will try on every notify and will go again in waiting state till condition satisfy
            this.wait();

        list.add(object);
        this.notifyAll(); // For all the thread waiting to dequeue
    }

    synchronized Object dequeue() throws InterruptedException {
        while (list.size() == 0)  // Thread will try on every notify and will go again in waiting state till condition satisfy
            this.wait();

        Object o = list.remove(0);
        this.notifyAll();  // For all the thread waiting to queue
        return o;
    }

}
