package util.concurrent.countDownLatch;

public class CountDownLatch {
    private int countDown; // No setter would be provided for countDown

    public CountDownLatch(int countDown) {
        this.countDown = countDown;
    }

    public synchronized boolean await() throws InterruptedException {
        while (countDown > 0)
            this.wait();
        return true;
    }

    public synchronized void countDown () {
        countDown --;
        if (countDown <= 0)
            this.notifyAll();
    }
}
