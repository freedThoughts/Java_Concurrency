package util.concurrent.practice2;

public class TwoThreadCounting {

    private static final String lock = new String("Lock");

    public static void main(String[] arg) {

        MyThread t1 = new MyThread(0, lock);
        MyThread t2 = new MyThread(1, lock);
        t1.start();
        t2.start();

    }

    static class MyThread extends Thread {
        int startCount;
        private final String lock;

        MyThread(int startCount, String lock) {
            this.startCount = startCount;
            this.lock = lock;
        }


        @Override
        public void run(){
            int i = this.startCount;

            synchronized (lock) {
                for(; i < 10; i = i+2){
                    System.out.println("By Thread " + Thread.currentThread().getName() + " " +i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }
}
