import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    public static void main(String[] args) {
        ReentrantLock mutex = new ReentrantLock();

        for (int i = 0; i < 5; i++) {
            int threadNumber = i + 1;
            new Thread(() -> {
                mutex.lock();
                System.out.println("Thread " + threadNumber + " has acquired the mutex.");
                try {
                    Thread.sleep(2000); // Simulated critical section
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                    System.out.println("Thread " + threadNumber + " has released the mutex.");
                }
            }).start();
        }
    }
}
