package util.concurrent.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {

    int maxThread;
    Queue<Runnable> tasks;
    List<Runnable> workers;
    boolean isStopped;

    ThreadPool(int maxThread){
        this.maxThread = maxThread;
        tasks = new LinkedList<>();
        workers = new ArrayList<>(maxThread);
        for(int i = 0; i < maxThread; i++) {
            Thread t = new Thread(
                    () -> {
                        while (!isStopped) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Runnable task = tasks.poll();
                            if (task != null) {
                                task.run();
                            }
                        }
                    }
            );
            t.start();
            workers.add(t);
        }

    }

    void execute(Runnable runnable) {
        tasks.add(runnable);
    }

    void stop(){
        this.isStopped = true;
    }


    public static void main(String[] arg) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5);
        for (int i = 0;  i < 10; i++)
            threadPool.execute( () -> System.out.println("From: " + Thread.currentThread().getName()));

        Thread.sleep(3000);
        threadPool.stop();
    }
}
