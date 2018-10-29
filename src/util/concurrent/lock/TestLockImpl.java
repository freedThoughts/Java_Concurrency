package util.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLockImpl {
    public static void main(String[] a){
        MyClass sharedObject = new MyClass();
        Lock lock = new Lock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        System.out.println( Thread.currentThread().getName()+  " " + sharedObject.incrementCount());
                    }
                    finally {
                        lock.unlock();
                    }
                }
            });
        }
        executorService.shutdown();
    }


    private static class MyClass {
        Integer myCount = 0;
        public Integer incrementCount(){
            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ++myCount;
        }
    }
}
