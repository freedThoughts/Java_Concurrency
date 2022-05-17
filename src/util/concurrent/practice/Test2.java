package util.concurrent.practice;

import util.concurrent.lock.Lock;

public class Test2 {

    public static void main(String[] arg) {

        //Common common = new Common();
        Lock lock = new Lock();
        Runnable runnable1 = () -> {
            int i = 0;

            synchronized (lock) {
                for(; i < 10; i = i+2) {
                    System.out.println("From : " + Thread.currentThread().getName() + " count : " + i);
                    try {
                        lock.wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };


        Runnable runnable2 = () -> {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };

            int i = 1;

            synchronized (lock) {
                for(; i < 10; i = i+2) {
                    System.out.println("From : " + Thread.currentThread().getName() + " count : " + i);
                    try {
                        lock.wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };




        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();

    }

    static class Common {

        synchronized void print(int i){
            for(; i < 10; i = i+2) {
                System.out.println("From : " + Thread.currentThread().getName() + " count : " + i);
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
