package util.concurrent.practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyExecutor {

    public static void main(String[] arg) throws InterruptedException {
        MyExecutorService myExecutorService = new MyExecutorService(5);
        for(int i = 0; i < 10; i++)
            myExecutorService.submit(
                    () -> {
                        System.out.println("Thread : " + Thread.currentThread().getName() + " running task ");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );

        Thread.sleep(50000);
        myExecutorService.stopIt();
    }


    static class MyExecutorService {

        int threadCount;
        List<MyThread> threads = new ArrayList<>();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        MyExecutorService(int threadCount) {
            this.threadCount = threadCount;
            for(int i = 0; i < threadCount; i++) {
                MyThread thread = new MyThread(queue);
                thread.start();

                threads.add(thread);
            }

        }

        void submit(Runnable runnable) throws InterruptedException {
            queue.put(runnable);
        }

        void stopIt(){
            for(MyThread thread : threads) {
                thread.isStopped = true;
                thread.interrupt();
            }
        }
    }


    static class MyThread extends Thread {

        boolean isStopped;
        BlockingQueue<Runnable> queue;

        MyThread(BlockingQueue<Runnable> queue) {
            this.queue = queue;

        }

        @Override
        public void run(){
            while (!isStopped) {
                try {
                    Runnable runnable = queue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    System.out.println("Thread intrupped: " + Thread.currentThread().getName());
                }
            }
        }

    }
}
