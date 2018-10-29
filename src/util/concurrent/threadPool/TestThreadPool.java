package util.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadPool {
    public static void main(String[] a){
        StringBuffer stringBuffer = new StringBuffer();
        ThreadPool threadPool = new ThreadPool(5, 10);
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i ++){
            final int taskId = i;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPool.execute(() ->{
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(stringBuffer.append(Thread.currentThread().getName() + "_taskId:" + taskId + " -> "));
            });
        }

        threadPool.shutdown();

    }
}
