import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    
    public Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        
        Process[] processes = new Process[n];
        
        System.out.println("Enter burst times for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Burst time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, burstTime);
        }
        
        // Calculate waiting times and turnaround times
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        
        waitingTime[0] = 0;
        turnaroundTime[0] = processes[0].burstTime;
        
        for (int i = 1; i < n; i++) {
            waitingTime[i] = turnaroundTime[i - 1];
            turnaroundTime[i] = waitingTime[i] + processes[i].burstTime;
        }
        
        // Calculate average waiting time and turnaround time
        double avgWaitingTime = 0;
        double avgTurnaroundTime = 0;
        
        for (int i = 0; i < n; i++) {
            avgWaitingTime += waitingTime[i];
            avgTurnaroundTime += turnaroundTime[i];
        }
        
        avgWaitingTime /= n;
        avgTurnaroundTime /= n;
        
        // Display results
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].pid + "\t\t" + processes[i].burstTime + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
        }
        
        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        
        scanner.close();
    }
}
