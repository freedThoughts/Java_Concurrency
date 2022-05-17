package util.concurrent.practice;

public class MyThread2 implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++)
            System.out.println("From: " + Thread.currentThread().getName()+ " Count: " + i);
    }
}
