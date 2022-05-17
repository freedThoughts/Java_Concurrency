package util.concurrent.practice2;

public class Test2 {

    public static void main(String[] arg) throws InterruptedException {
        System.out.println("fvfss");

/*        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        t1.start();
        t2.start();*/





        Runnable runnable2 = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        Thread t2 = new Thread(runnable2, "Thread-2");

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(4000);
                Thread.yield();
                Thread.yield();
                Thread.yield();
                Thread.yield();
                Thread.yield();
                //t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            System.out.println(Thread.currentThread().getName());
        };


        Thread t1 = new Thread(runnable1, "Thread-1");

        t1.start();
        Thread.sleep(2000);
        t2.start();
    }


    static class MyThread extends Thread {

        public void run(){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
