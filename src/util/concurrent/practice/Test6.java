package util.concurrent.practice;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test6 {

    public static void main(String[] arg) {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("Barrier executed"));
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Barrier invoked  " + Thread.currentThread().getName());
        };

        Runnable runnable2 = () -> {
            try {
                //Thread.sleep(3000);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Barrier invoked  " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable2);
        t2.start();
        t1.start();
    }


}
