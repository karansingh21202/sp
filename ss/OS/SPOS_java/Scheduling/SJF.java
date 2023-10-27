import java.util.Arrays;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    
    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class SJFScheduling {
    public static void main(String[] args) {
        Process[] processes = {
            new Process(1, 0, 6),
            new Process(2, 1, 8),
            new Process(3, 2, 7),
            new Process(4, 3, 3)
        };

        int n = processes.length;
        
        // Sort the processes by arrival time
        Arrays.sort(processes, (a, b) -> Integer.compare(a.arrivalTime, b.arrivalTime));
        
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        
        completionTime[0] = processes[0].burstTime;
        waitingTime[0] = 0;
        
        for (int i = 1; i < n; i++) {
            completionTime[i] = completionTime[i - 1] + processes[i].burstTime;
            waitingTime[i] = completionTime[i - 1] - processes[i].arrivalTime;
            if (waitingTime[i] < 0) {
                waitingTime[i] = 0;
            }
        }
        
        int totalWaitingTime = Arrays.stream(waitingTime).sum();
        int totalTurnaroundTime = Arrays.stream(completionTime).sum();
        
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;
        
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].pid + "\t\t" + processes[i].arrivalTime + "\t\t" + processes[i].burstTime
                    + "\t\t" + completionTime[i] + "\t\t" + waitingTime[i]);
        }
        
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }
}
