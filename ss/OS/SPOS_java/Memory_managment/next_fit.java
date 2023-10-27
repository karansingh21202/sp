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

public class NextFitMemoryAllocation {
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

        int lastAllocatedBlockIndex = 0;

        // Allocate memory to processes using Next Fit
        for (Process process : processes) {
            for (int i = lastAllocatedBlockIndex; i < memoryBlocks.size(); i++) {
                MemoryBlock memoryBlock = memoryBlocks.get(i);
                if (!memoryBlock.allocated && memoryBlock.size >= process.size) {
                    memoryBlock.allocated = true;
                    process.allocatedBlockId = memoryBlock.id;
                    lastAllocatedBlockIndex = i;
                    break;
                }
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
