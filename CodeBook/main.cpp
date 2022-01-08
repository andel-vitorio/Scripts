#include <bits/stdc++.h>

using namespace std;

long int fat[10] = {362880, 40320, 5040, 720, 120, 24, 6, 2, 2, 1};

long int solve (long int n) {
    int idx = 9;
    long int ans = 0;
    while (n > 0L) {
        n %= fat[idx];
        ans++; idx--;
        puts("AAAAAAAAAAA");
    }

    return ans;
}

int main (void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    long int n;
    cin >> n;

    long int ans = 0;
    printf("%ld\n", ans);

    return 0;
}