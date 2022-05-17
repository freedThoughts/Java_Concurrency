/*
package util.concurrent.practice2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

public class Test4 {

    public static void main(String[] arg) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Producer producer = new Producer(executorService);
        Consumer consumer = new Consumer(executorService);


        Thread.sleep(50000);
        consumer.setFinished();


    }


    static class Producer {
        ExecutorService executorService;

        Producer(ExecutorService executorService){
            this.executorService = executorService;
        }

        public void produce(){
            for(int i = 0; i < 20; i++) {
                try {
                    executorService.submit(() -> System.out.println("Thread: " + Thread.currentThread().getName() + " printing ..."));
                    //queue.put(() -> System.out.println("Thread: " + Thread.currentThread().getName() + " printing ..."));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    static class Consumer{

        boolean isFinished;
        ExecutorService executorService;

        Consumer(ExecutorService executorService){
            this.executorService = executorService;
        }

        void setFinished(){
            this.isFinished = true;
        }

        void startConsuming() throws InterruptedException {

            while (!isFinished) {
                executorService.execute();
                Runnable runnable = null;
                try {
                    runnable = queue.take();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runnable.run();

            }

            executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        }

    }
}
*/
