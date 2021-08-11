
public class Threads2 {
    public static void main(String[] args) throws InterruptedException {
        var storage = new Storage();
        var printer = new Printer(storage);
        var counter = new Counter(storage);
        // Sleep so that Printer and Counter threads have some time to do their job.
        Thread.sleep(50);
    }
}

/**
 * Implements a state that is shared between threads.
 */
final class Storage {
    /**
     * Shared state.
     */
    private int num = 0;

    private boolean printed = false;

    public synchronized int getNum() {
        while (printed) {
            try {
                // Wait until we have a new unprinted num.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Unexpected interrupt.", e);
            }
        }

        printed = true;
        notify();

        return num;
    }

    public synchronized void setNum(int num) {
        while (!printed) {
            try {
                // Wait until the current num is printed, so we can assign the new value.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Unexpected interrupt.", e);
            }
        }

        this.num = num;

        printed = false;
        notify();
    }
}

/**
 * Starts a thread that runs an infinite loop that sets the storage value to consecutive integers 0, 1, 2...
 */
final class Counter extends Thread {
    private final Storage storage;

    public Counter(Storage storage) {
        this.storage = storage;
        setName("counter");
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        for (int i = 1;; i++) {
            storage.setNum(i);
        }
    }
}

/**
 * Starts a thread that runs an infinite loop that prints the current storage value.
 **/
final class Printer extends Thread {
    private final Storage storage;

    public Printer(Storage storage) {
        this.storage = storage;
        setName("printer");
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(storage.getNum() + " ");
        }
    }
}
