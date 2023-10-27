import java.util.Arrays;

class Process {
    int pid;
    int arrivalTime;
    int burstTime;
    int priority;
    
    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Process[] processes = {
            new Process(1, 0, 6, 2),
            new Process(2, 1, 8, 1),
            new Process(3, 2, 7, 3),
            new Process(4, 3, 3, 4)
        };

        int n = processes.length;

        // Sort the processes by priority and arrival time
        Arrays.sort(processes, (a, b) -> {
            if (a.priority != b.priority) {
                return a.priority - b.priority;
            }
            return a.arrivalTime - b.arrivalTime;
        });

        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];

        completionTime[0] = processes[0].burstTime;
        waitingTime[0] = 0;

        for (int i = 1; i < n; i++) {
            completionTime[i] = completionTime[i - 1] + processes[i].burstTime;
            waitingTime[i] = completionTime[i - 1] - processes[i].arrivalTime;
        }

        int totalWaitingTime = Arrays.stream(waitingTime).sum();
        int totalTurnaroundTime = Arrays.stream(completionTime).sum();

        double averageWaitingTime = (double) totalWaitingTime / n;
        double averageTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("Process\tArrival Time\tBurst Time\tPriority\tCompletion Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].pid + "\t\t" + processes[i].arrivalTime + "\t\t" + processes[i].burstTime
                    + "\t\t" + processes[i].priority + "\t\t" + completionTime[i] + "\t\t" + waitingTime[i]);
        }

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }
}
