#include <bits/stdc++.h>

using namespace std;

long long int solve ( vector<string> mylist ) {
    long long int ans = 0;
    string str_tmp;

    sort(mylist.begin(), mylist.end());

    str_tmp = mylist[0];

    for ( int x = 1; x < mylist.size(); x++ ) {
        for ( int y = 0; y < mylist[x].size(); y++ ) {
            if ( str_tmp[0] != mylist[x][0] ) {
                str_tmp = mylist[x];
                break;
            } else if ( str_tmp[y] == mylist[x][y] ) {
                ans++;
            } else {
                str_tmp = mylist[x];
                break;
            }
        }
    }
    
    return ans;
}

int main ( void ) {
    vector<string> mylist;
    long long int ans, n;
    string str;

    while ( cin >> n ) {
        while ( n-- and cin >> str )
            mylist.push_back(str);
        
        ans = solve(mylist);
        
        cout << ans << '\n';

        mylist.clear();
    }

    return 0;
}
