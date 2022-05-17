package util.concurrent.practice;

import util.concurrent.countDownLatch.CountDownLatch;

public class Test5 {
    public static void main(String[] arg) {
        CountDownLatch latch = new CountDownLatch(2);
        Runnable waiterRunnable = () -> {
            try {
                System.out.println("Invoked Waiter");
                latch.await();
                System.out.println("Waiter finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable counter = () -> {
            try {
                System.out.println("Invoked counter : " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        };

        Thread waiter = new Thread(waiterRunnable);
        waiter.start();

        Thread counterThread1 = new Thread(counter);
        Thread counterThread2 = new Thread(counter);
        counterThread1.start();
        counterThread2.start();

    }
}
