#include <iostream>
#include <vector>
#include <set>
using namespace std;

int main() {
    int frames, pages;
    cout << "Enter the number of frames: ";
    cin >> frames;
    
    cout << "Enter the number of pages: ";
    cin >> pages;
    
    vector<int> pageFrames(frames, -1);
    set<int> remainingPages;
    int page, pageFaults = 0;
    
    cout << "Enter the page references: ";
    for (int i = 0; i < pages; i++) {
        cin >> page;
        if (remainingPages.find(page) != remainingPages.end()) {
            remainingPages.erase(page);
        } else {
            if (pageFrames[pageFaults % frames] != -1) {
                remainingPages.erase(pageFrames[pageFaults % frames]);
            }
            remainingPages.insert(page);
            pageFrames[pageFaults % frames] = page;
            pageFaults++;
        }
    }
    
    cout << "Total Page Faults: " << pageFaults << endl;
    
    return 0;
}
