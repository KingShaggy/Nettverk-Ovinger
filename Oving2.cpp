#include <iostream>
#include <vector>
#include <thread>
#include <condition_variable>

using namespace std;


int main()
{
    Workers worker_threads(4);
    Workers event_loop(1);

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