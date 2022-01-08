#include <bits/stdc++.h>

using namespace std;

int main (void) {
    long long int n, range;
    srand(time(NULL));

    cin >> n >> range;

    while (n--) cout << rand() % range << endl;
  
    return 0;
}