import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1); // Semaphore with one permit

        for (int i = 0; i < 5; i++) {
            int userNumber = i + 1;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("User " + userNumber + " is using a resource.");
                    Thread.sleep(2000); // Simulated resource usage time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println("User " + userNumber + " has released the resource.");
                }
            }).start();
        }
    }
}
