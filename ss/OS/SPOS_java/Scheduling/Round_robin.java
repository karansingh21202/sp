import java.util.LinkedList;
import java.util.Queue;

class Process {
    int pid;
    int burstTime;
    
    public Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Queue<Process> queue = new LinkedList<>();
        
        Process[] processes = {
            new Process(1, 10),
            new Process(2, 5),
            new Process(3, 8),
            new Process(4, 12)
        };

        int quantum = 2; // Time quantum

        for (Process process : processes) {
            queue.add(process);
        }

        System.out.println("Process\tRemaining Time");

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            int remainingTime = currentProcess.burstTime > quantum ? currentProcess.burstTime - quantum : 0;

            System.out.println(currentProcess.pid + "\t\t" + remainingTime);

            currentProcess.burstTime = remainingTime;

            if (remainingTime > 0) {
                queue.add(currentProcess);
            }
        }
    }
}
