#include <bits/stdc++.h>

using namespace std;

int binary_search (vector <int> array, int elem) {
    int index = 0;

    for (int b = array.size(); b >= 1; b /= 2) {
        while (index + b < array.size() and array[index + b] <= elem)
            index += b;
    }

    if (array[index] == elem) {
        // elemento encontrado
        return index;
    } else return -1;
}

int binary_search_2 (vector <int> array, int elem) {
    vector <int> ::iterator low, up;
    low = lower_bound(array.begin(), array.end(), elem);
    up = upper_bound(array.begin(), array.end(), elem);

    cout << "lower_bound at position " << (low- array.begin()) << '\n';
    cout << "upper_bound at position " << (up - array.begin()) << '\n';
    return 0;
}

int main (void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    vector <int> array;
    int elem;

    for (int idx = 0; idx < 10 and cin >> elem; idx++) {
        array.push_back(elem);
    } cin >> elem;

    cout << binary_search_2(array, elem) << '\n';
    
    return 0;
}