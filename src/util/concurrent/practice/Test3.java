package util.concurrent.practice;

import util.concurrent.lock.Lock;

// Dead lock
public class Test3 {

    public static void main(String[] arg) {
        Lock lock1 = new Lock();
        Lock lock2 = new Lock();

        Runnable runnable1 = () -> {
            lock1.lock();
            System.out.println("From : " + Thread.currentThread().getName() + " lock1 locked");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
            System.out.println("From : " + Thread.currentThread().getName() + " lock2 locked");
            lock2.unlock();
            lock1.unlock();

        };

        Runnable runnable2 = () -> {
            lock2.lock();
            System.out.println("From : " + Thread.currentThread().getName() + " lock2 locked");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.lock();
            System.out.println("From : " + Thread.currentThread().getName() + " lock1 locked");
            lock1.unlock();
            lock2.unlock();

        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();
    }
}
