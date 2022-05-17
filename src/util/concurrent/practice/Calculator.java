package util.concurrent.practice;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Calculator {

    double value;

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    ReadWriteLock readWriteLock2 = new ReentrantReadWriteLock(true);


    public static void main(String[] arg) {



    }


    void addition(double val){

        readWriteLock.writeLock().lock();

        try{
            value = val + value;
        } finally{
            readWriteLock.writeLock().unlock();
        }

    }

    void substraction(double val) {
        readWriteLock.writeLock().lock();
        try{
            value = value - val;
        } finally{
            readWriteLock.writeLock().unlock();
        }
    }

    double getValue() {

        readWriteLock.readLock().lock();
        try{
            return value;
        } finally{
            readWriteLock.readLock().unlock();
        }

    }



}
