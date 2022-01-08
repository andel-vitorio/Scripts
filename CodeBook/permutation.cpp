#include <bits/stdc++.h>

using namespace std;


void solve (vector <int> array) {
    do {
        //process
        for (int &elem: array) {
            cout << elem << " ";
        }
        cout << '\n';
    } while (next_permutation(array.begin(), array.end()));
}

int main (void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    vector <int> array;
    int elem;

    for (int idx = 0; idx < 3 and cin >> elem; idx++) {
        array.push_back(elem);
    } 

    solve(array);
    
    return 0;
}