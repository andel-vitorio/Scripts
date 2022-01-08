#include <bits/stdc++.h>

using namespace std;

int fibonacci (int pos) {
    if (pos < 2) return pos;
    else return fibonacci(pos - 1) + fibonacci(pos - 2);
}

int main (void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int pos;
    
    while (cin >> pos) {
        cout << fibonacci(pos) << endl;
    }

    return 0;
}