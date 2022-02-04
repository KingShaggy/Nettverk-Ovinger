#include <iostream>
#include <vector>
#include <thread>
#include <condition_variable>
#include <list>
#include <mutex>
#include <functional>
#include <chrono>
 
using namespace std;
using namespace chrono;
 
class Workers {
private: 
    int numOfThreads;
    condition_variable cv;
    vector<thread> threads;
    list<function<void()>> tasks;
    mutex tasks_mutex;
    bool run = true;
    bool waiting;
 
public:
    Workers(int num) {
        numOfThreads = num;
    }
 
    void start() {
        for(int i = 0; i < numOfThreads; i++){
            threads.emplace_back([this] {
                while(run) {
                    function<void()> task;
                    {
                        unique_lock<mutex> lock(tasks_mutex);
                        while(waiting) {
                            cv.wait(lock);
                        }
                        waiting = true;
                        if(!tasks.empty()) {
                            task = *tasks.begin();
                            tasks.pop_front();
                        }
                        lock.unlock();
                        waiting = false;
                        cv.notify_one();
                    }
                    if(task) {
                        task();
                    }
                }
            });
        }
        cv.notify_one();
    }
 
    void post(function<void()> f) {
        unique_lock<mutex> lock(tasks_mutex);
        tasks.emplace_back(f);
        lock.unlock();
        waiting = false;
        cv.notify_one();
    }
 
    void stop() {
        run = false;
        for(auto& t : threads){
            t.join();
        }
    }
 
    void post_timeout(const function<void()>& function, int t) {
        this_thread::sleep_for(milliseconds (t));
        unique_lock<mutex> lock(tasks_mutex);
        tasks.emplace_back(function);
        waiting = false;
        cv.notify_one();
    }
};
 
int main() {
    Workers worker_threads(4);
    Workers event_loop(1);
 
    worker_threads.start(); 
    event_loop.start(); 
 
    worker_threads.post([] {
        cout << "A" << endl;
    });
 
    worker_threads.post([] {
        cout << "B" << endl;
    });
 
    event_loop.post([] {
        cout << "C" << endl;
    });
 
    event_loop.post([] {
        cout << "D" << endl;
    });

    worker_threads.post_timeout([] {
        cout << "Timeout thread_id: " << this_thread::get_id() << endl;
    }, 1000);

    worker_threads.stop();
    event_loop.stop();
}