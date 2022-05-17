package util.concurrent.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test7 {
    public static void main(String[] arg) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++)
          executorService.submit(() -> {
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("Running: " + Thread.currentThread().getName());
          });

        //executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        executorService.shutdown();


    }
}
