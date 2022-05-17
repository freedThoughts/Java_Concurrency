package util.concurrent.practice;

import util.concurrent.lock.Lock;

public class Test1 {

    public static void main(String[] arg) {


/*        Thread t1 = new MyThread1();
        t1.setName("T1");
        t1.start();

 */

        Lock lock = new Lock();

        Common common = new Common();

        Runnable runnable2 = () -> {
            System.out.println("Entering to print by; " + Thread.currentThread().getName());

            lock.lock();
            common.display();
            lock.unlock();
        };

        Thread t2 = new Thread(runnable2);
        t2.setName("t2");

        Runnable runnable3 = () -> {

            System.out.println("Entering to print by; " + Thread.currentThread().getName());

            lock.lock();
            common.display();
            lock.unlock();
        };



        Thread t3 = new Thread(runnable3);
        t3.setName("t3");

        t3.start();
        t2.start();

/*
        for (int i = 0; i < 10; i++)
            System.out.println("From: " + Thread.currentThread().getName() +  " Count: " + i);*/

    }


    static class Common {
        public void display(){


            for(int i = 0; i < 5; i++)
                System.out.println("From: " + Thread.currentThread().getName()+ " Count: " + i);
        }
    }
}
