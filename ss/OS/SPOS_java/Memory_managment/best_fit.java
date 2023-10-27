import java.util.ArrayList;
import java.util.Scanner;

class MemoryBlock {
    int id;
    int size;
    boolean allocated;

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

class Process {
    int id;
    int size;
    int allocatedBlockId;

    public Process(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocatedBlockId = -1;
    }
}

public class BestFitMemoryAllocation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList();

        // Input the number of memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        for (int i = 1; i <= numBlocks; i++) {
            System.out.print("Enter the size of memory block " + i + ": ");
            int blockSize = scanner.nextInt();
            memoryBlocks.add(new MemoryBlock(i, blockSize));
        }

        // Input the number of processes
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        for (int i = 1; i <= numProcesses; i++) {
            System.out.print("Enter the size of process " + i + ": ");
            int processSize = scanner.nextInt();
            processes.add(new Process(i, processSize));
        }

        // Allocate memory to processes using Best Fit
        for (Process process : processes) {
            int bestFitIndex = -1;
            for (int i = 0; i < memoryBlocks.size(); i++) {
                if (!memoryBlocks.get(i).allocated && memoryBlocks.get(i).size >= process.size) {
                    if (bestFitIndex == -1 || memoryBlocks.get(i).size < memoryBlocks.get(bestFitIndex).size) {
                        bestFitIndex = i;
                    }
                }
            }

            if (bestFitIndex != -1) {
                MemoryBlock bestFitBlock = memoryBlocks.get(bestFitIndex);
                bestFitBlock.allocated = true;
                process.allocatedBlockId = bestFitBlock.id;
            }
        }

        // Display allocation results
        System.out.println("\nMemory Allocation Results:");
        for (Process process : processes) {
            if (process.allocatedBlockId != -1) {
                System.out.println("Process " + process.id + " is allocated to Memory Block " + process.allocatedBlockId);
            } else {
                System.out.println("Process " + process.id + " cannot be allocated to any memory block.");
            }
        }

        scanner.close();
    }
}
