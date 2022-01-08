#include <bits/stdc++.h>

using namespace std;

int bestSum (vector <int> array) {
    int sum = 0, best = 0;

    for (int idx = 0; idx < array.size(); idx++) {
        sum = max(array[idx], sum + array[idx]);
        best = max(best, sum);  
    }      

    return best;
}

int main (void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    vector <int> array;
    int elem;

    while (cin >> elem) {
        array.push_back(elem);
    }
    
    cout << bestSum(array) << '\n';

    return 0;
}