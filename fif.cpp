#include <iostream>
#include <queue>

int main() {
    std::queue<int> myQueue;

    // Adding elements to the queue
    myQueue.push(1);
    myQueue.push(2);
    myQueue.push(3);

    int target = 4; // Element to check for

    bool found = false;

    // Check if the element is present in the queue
    while (!myQueue.empty()) {
        int element = myQueue.front();
        if (element == target) {
            found = true;
            break;
        }
        myQueue.pop();
        myQueue.push(element);
    }

    if (found) {
        std::cout << "Hit!" << std::endl;
    } else {
        std::cout << "Not found. Replacing the first element with " << target << std::endl;
        myQueue.pop();
        myQueue.push(target);
    }

    // Display the modified queue
    std::cout << "Queue after modification:" << std::endl;
    while (!myQueue.empty()) {
        int element = myQueue.front();
        std::cout << element << " ";
        myQueue.pop();
    }
    std::cout << std::endl;

    return 0;
}
