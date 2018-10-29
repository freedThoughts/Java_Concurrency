package util.concurrent.lock;

public class Lock {
    private boolean isLocked;
    private int counter;
    private Thread lockedHoldBy;

    public synchronized void lock() {
        while(isLocked && lockedHoldBy != Thread.currentThread()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isLocked = true;
        counter ++;
        lockedHoldBy = Thread.currentThread();
    }

    public synchronized void unlock() {
        if(lockedHoldBy == Thread.currentThread())
            counter--;

        if(counter == 0){
            isLocked = false;
            lockedHoldBy = null;
            this.notifyAll();
        }
    }
}
