package util.concurrent.practice;

import java.util.HashMap;
import java.util.Map;

public class MyReadWriteLock {

    Map<Thread, Integer> readers;
    Thread writer;
    int writerCount;
    int writeRequestCount;

    MyReadWriteLock() {
        readers = new HashMap<>();
    }


    synchronized void readLock() throws InterruptedException {
        while (writeRequestCount > 0 || writerCount > 0)
            wait();

        Thread currentThread = Thread.currentThread();
        if (readers.containsKey(currentThread)) {
            readers.put(currentThread, readers.get(currentThread)+1);
            return;
        }

        readers.put(currentThread, 1);
    }

    synchronized void readUnlock(){
        Thread currentThread = Thread.currentThread();
        if (!readers.containsKey(currentThread)) throw  new IllegalMonitorStateException("");

        Integer readerCount = readers.get(currentThread);
        readerCount--;
        if (readerCount != 0) {
            readers.put(currentThread, readerCount);
        } else {
            readers.remove(currentThread);
            notifyAll();
        }
    }

    synchronized void writeLock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        writeRequestCount++;
        while (readers.size() > 0 || (writer != null && writer !=  currentThread)) {
            wait();
        }
        writeRequestCount--;
        writerCount++;
        if (writer == null) {
            writer = currentThread;
        }

    }

    synchronized void writeUnlock(){
        Thread currentThread = Thread.currentThread();
        if (writer != currentThread) throw  new IllegalMonitorStateException("");
        writerCount--;
        if (writerCount == 0) {
            writer = null;
            notifyAll();
        }
    }
}
