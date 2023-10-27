#include <iostream>
#include <list>
#include <unordered_map>
using namespace std;

int main() {
    int frames, pages;
    cout << "Enter the number of frames: ";
    cin >> frames;
    
    cout << "Enter the number of pages: ";
    cin >> pages;
    
    list<int> pageList;
    unordered_map<int, list<int>::iterator> pageMap;
    int page, pageFaults = 0;
    
    cout << "Enter the page references: ";
    for (int i = 0; i < pages; i++) {
        cin >> page;
        if (pageMap.find(page) != pageMap.end()) {
            pageList.erase(pageMap[page]);
        } else {
            if (pageList.size() >= frames) {
                pageMap.erase(pageList.back());
                pageList.pop_back();
            }
            pageFaults++;
        }
        pageList.push_front(page);
        pageMap[page] = pageList.begin();
    }
    
    cout << "Total Page Faults: " << pageFaults << endl;
    
    return 0;
}
