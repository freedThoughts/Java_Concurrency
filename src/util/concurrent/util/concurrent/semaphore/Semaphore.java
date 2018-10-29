package util.concurrent.util.concurrent.semaphore;

public class Semaphore {
    private int permit;
    private int permitGranted;

    public Semaphore(int permit){
        this.permit = permit;
    }

    public synchronized void acquire() throws InterruptedException{
        while (permitGranted == permit)
            this.wait();

        permitGranted++;
    }

    public synchronized void release() throws InterruptedException {
        if (permitGranted <= 0)
            throw new IllegalMonitorStateException("No Permit is granted ");
        permitGranted--;
        this.notifyAll();
    }
}
