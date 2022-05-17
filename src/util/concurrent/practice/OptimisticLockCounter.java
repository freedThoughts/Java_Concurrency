package util.concurrent.practice;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLockCounter {

    AtomicInteger counter = new AtomicInteger(0);

    void increment(){
        boolean isSuccess = false;
        while (!isSuccess) {
            int val = counter.get();
            isSuccess = counter.compareAndSet(val, val+1);
        }
    }

    public static void main(String[] arg) {

    }
}
