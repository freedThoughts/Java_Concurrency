package util.concurrent.practice;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test4 {

    public static void main(String[] arg) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        readWriteLock.readLock().lock();
        try {
            //
        } finally{
            readWriteLock.readLock().unlock();
        }


        readWriteLock.writeLock().lock();
        try {
            //
        } finally{
            readWriteLock.writeLock().unlock();
        }
    }


}
