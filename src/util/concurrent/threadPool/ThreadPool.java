package util.concurrent.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool {

    private BlockingQueue taskQueue;
    private  List<MyThread> myThreadList;
    private AtomicBoolean isStopped;

    ThreadPool(int numberOfThreads, int maxTaskLimit){
        this.taskQueue = new ArrayBlockingQueue(maxTaskLimit);
        this.myThreadList = new ArrayList<>(numberOfThreads);
        this.isStopped = new AtomicBoolean(false);
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myThread = new MyThread(taskQueue, isStopped);
            myThreadList.add(myThread);
            myThread.start();
        }
    }

    public void execute(Runnable runnable){
        taskQueue.offer(runnable);
    }

    void shutdown(){
        isStopped.set(true);
    }

    private class MyThread extends Thread {
        private BlockingQueue blockingQueue;
        private AtomicBoolean isStopped;
        MyThread(BlockingQueue blockingQueue, AtomicBoolean isStopped){
            this.blockingQueue =  blockingQueue;
            this.isStopped = isStopped;
        }

        @Override
        public void run() {
            Runnable runnable = null;
            while(!isStopped.get()) {
                try {
                    runnable = (Runnable) blockingQueue.poll(200, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(runnable != null)
                    runnable.run();
            }
        }
    }
}
