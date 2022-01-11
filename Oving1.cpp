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

void FindPrimes(int start, int end, vector<int> &primes){   //& makes you pass a referance to the original primes variable, any changes done in the function will change the original
    while(start < end){
        if (check_if_prime(start)){
            primes.push_back(start);
        }
        ++start;
    }
}

int main()
{
    vector<int> primes;

    FindPrimes(0, 100, primes);

    for (auto i: primes){
        cout << i << " ";
    }

    return 0;
}