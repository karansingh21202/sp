#include <iostream>
#include <vector>

int main() {
    int n;
    std::cout << "Enter the number of memory blocks: ";
    std::cin >> n;

    std::vector<int> memory(n);

    std::cout << "Now enter the size of the memory blocks: " << std::endl;
    for (int i = 0; i < n; i++) {
        std::cin >> memory[i];
    }

    int p;
    std::cout << "Enter the number of processes: ";
    std::cin >> p;

    std::vector<int> processes(p);
    std::vector<int> allocation(p, -1);

    for (int i = 0; i < p; i++) {
        std::cout << "Enter the size of process " << i + 1 << ": ";
        std::cin >> processes[i];
    }

    for (int j = 0; j < p; j++) {
        for (int i = 0; i < n; i++) {
            if (allocation[j] == -1 && processes[j] <= memory[i]) {
                allocation[j] = i;
                break;  // First Fit: Allocate the first block that fits.
            }
        }
    }

    for (int i = 0; i < p; i++) {
        if (allocation[i] != -1)
            std::cout << "Process " << i + 1 << " is allocated to memory block " << allocation[i] + 1 << std::endl;
        else
            std::cout << "Process " << i + 1 << " cannot be allocated" << std::endl;
    }

    return 0;
}
