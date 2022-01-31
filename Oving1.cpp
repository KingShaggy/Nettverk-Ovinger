#include <iostream>
#include <vector>
#include <thread>
#include <mutex>


using namespace std;

vector<int> primes;
mutex prime_mutex;

bool check_if_prime(int num){
    if (num == 0 || num == 1){
        return false;
    }
    else {
        for (int i = 2; i <= num / 2; ++i){
            if (num % i == 0){
                return false;
            }
        }
    }
    return true;
}

void FindPrimes(int start, int end){
    while(start < end){
        if (check_if_prime(start)){
            prime_mutex.lock();
            primes.push_back(start);
            prime_mutex.unlock();
        }
        ++start;
    }
}

void UseThreads(int start, int end, int numOfThreads){
    vector<thread> threads;

    int numPerThread = (end - start) / numOfThreads;
    int localEnd = start + numPerThread;

    for (int i = 0; i < numOfThreads; i++){
        threads.emplace_back(FindPrimes, start, localEnd);
        start += numPerThread;
        localEnd += numPerThread;
    }

    for (auto& t : threads){
        t.join();
    }
}

int main()
{
    UseThreads(0, 100, 3);

    for (auto i: primes){
        cout << i << " ";
    }

    return 0;
}