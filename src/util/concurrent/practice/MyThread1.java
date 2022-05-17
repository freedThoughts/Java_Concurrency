package util.concurrent.practice;

public class MyThread1 extends Thread {


    synchronized void display(){
        for(int i = 0; i < 5; i++)
            System.out.println("From: " + Thread.currentThread().getName()+ " Count: " + i);
    }

    @Override
    public void run(){
/*        for(int i = 0; i < 10; i++)
            System.out.println("From: " + Thread.currentThread().getName()+ " Count: " + i);*/
    }
}
