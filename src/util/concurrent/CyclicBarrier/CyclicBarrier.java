package util.concurrent.CyclicBarrier;

public class CyclicBarrier {

    private int availableParties = 0;
    private int requiredParties;

    public CyclicBarrier (int requiredParties) {
        this.requiredParties = requiredParties;
    }


    public synchronized int await() throws InterruptedException {
        availableParties ++;
        while (requiredParties < availableParties)
            this.wait();
        this.notifyAll();
        return 0;
    }

    public int getParties() {
        return availableParties;
    }

}
