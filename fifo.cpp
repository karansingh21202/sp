#include <iostream>
#include <queue>
using namespace std;

int main() {
    int frames, pages;
    cout << "Enter the number of frames: ";
    cin >> frames;
    
    cout << "Enter the number of pages: ";
    cin >> pages;
    
    queue<int> pageQueue;
    int page, pageFaults = 0;
    
    cout << "Enter the page references: ";
    for (int i = 0; i < pages; i++) {
        cin >> page;
        if (pageQueue.size() < frames) {
            pageQueue.push(page);
            pageFaults++;
        } else {
            if (pageQueue.front() != page) {
                pageQueue.pop();
                pageQueue.push(page);
                pageFaults++;
            }
        }
    }
    
    cout << "Total Page Faults: " << pageFaults << endl;
    
    return 0;
}
