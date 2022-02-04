#include <iostream>
#include <vector>
#include <thread>
#include <condition_variable>
#include <list>
#include <mutex>

using namespace std;

class Workers {
private:
    int numOfThreads;
    condition_variable cv;
    vector<thread> threads;
    list<function<void>> tasks;
    mutex tasks_mutex;

public:
    Workers(int num) {
        numOfThreads = num;
    }

    void start() {
        
    }
};

int main()
{
    Workers worker_threads(4);
    Workers event_loop(1);

    worker_threads.start();
    event_loop.start();


    worker_threads.post([] {
        cout << "Task A" << endl;
    });

    worker_threads.post([] {
        cout << "Task B" << endl;
    });

    event_loop.post([] {
        cout << "Task C" << endl;
    });

    event_loop.post([] {
        cout << "Task D" << endl;
    });

    worker_threads.join();
    event_loop.join();
}