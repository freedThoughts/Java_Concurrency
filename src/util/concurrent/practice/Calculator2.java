package util.concurrent.practice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Calculator2 {

    BigDecimal result = new BigDecimal(0.0);
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    AtomicReference<BigDecimal> doubleAtomicReference = new AtomicReference<BigDecimal>(new BigDecimal(0.0));

    AtomicReferenceArray<Double> atomicReferenceArray = new AtomicReferenceArray<>(new Double[16]);


    void add(BigDecimal val) {
        boolean isExecuted = false;
        while (!isExecuted) {
            BigDecimal currentVal = doubleAtomicReference.get();
            BigDecimal newVal = currentVal.add(val).setScale(3, BigDecimal.ROUND_CEILING);
            isExecuted = doubleAtomicReference.compareAndSet(currentVal,newVal);
        }
    }

/*    void add(BigDecimal val) {
        readWriteLock.writeLock().lock();
        try{
            result = result.add(val).setScale(3, BigDecimal.ROUND_CEILING);
        } finally{
            readWriteLock.writeLock().unlock();
        }

    }

    void subs(BigDecimal val) {
        result = result.subtract(val).setScale(3, BigDecimal.ROUND_CEILING);
    }*/

}
