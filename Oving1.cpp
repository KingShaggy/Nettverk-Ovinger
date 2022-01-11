#include <iostream>
#include <vector>
#include <thread>


using namespace std;

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

int main()
{
    vector<thread> threads;
    vector<int> primes;

    int start = 0;
    int end = 100;

    while(start < end){
        if (check_if_prime(start)){
            primes.push_back(start);
        }
        ++start;
    }

    for (auto i: primes){
        cout << i << " ";
    }

    return 0;
}