package util.concurrent.practice2;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class test1 {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    BlockingQueue<Employee> blockingQueue = new ArrayBlockingQueue(10);
    BlockingQueue blockingQueue2 = new LinkedBlockingQueue();
    BlockingQueue blockingQueue3 = new PriorityBlockingQueue();
    Queue queue = new PriorityQueue();


    CountDownLatch latch = new CountDownLatch(2);



    public static void main(String[] arg) {

    }

    void doIt(){




        readWriteLock.readLock().lock();

        try{
            System.out.println("reading");

/*            blockingQueue.put(new Employee());
            blockingQueue.take();*/

        } finally{
            readWriteLock.readLock().unlock();
        }




        readWriteLock.writeLock().lock();

        try{
            System.out.println("writing");

        } finally{
            readWriteLock.writeLock().unlock();
        }




    }
}
