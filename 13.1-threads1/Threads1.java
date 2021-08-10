
public class Threads1 {
    public static void main(String[] args) throws InterruptedException {
        var storage = new Storage();
        var printer = new Printer(storage);
        var counter = new Counter(storage);
        // Sleep so that Printer and Counter threads have some time to do their job.
        Thread.sleep(2000);
    }
}

/**
 * Storage for the int value shared between threads.
 */
final class Storage {
    private volatile int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

/**
 * Starts a thread that runs an infinite loop that sets the storage value to consecutive integers 0, 1, 2...
 *
 * After each loop iteration the thread sleep for a random time between 50 and 150 milliseconds.
 */
final class Counter extends Thread {
    private final Storage storage;

    public Counter(Storage storage) {
        this.storage = storage;
        // Set up the thread.
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        for (int i = 0;; i++) {
            storage.setNum(i);

            try {
                sleep(50 + (int)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * Starts a thread that runs an infinite loop that prints the current storage value.
 *
 * After each loop iteration the thread sleeps 100 milliseconds.
 **/
final class Printer extends Thread {
    private final Storage storage;

    public Printer(Storage storage) {
        this.storage = storage;
        // Set up the thread.
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(storage.getNum() + " ");

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
