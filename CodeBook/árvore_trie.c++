#include <bits/stdc++.h>

using namespace std;

struct Trie {
    Trie *next[27]; // ou 11 caso for um numero
    bool end;
    Trie() {
        memset(next, 0, sizeof(next));
        end = false;
    }
};

void insert ( Trie *root, string str ) {
    int c;

    for ( char ch: str ) {
        c = ch - 'a'; // ou '0' caso for um numero
        if ( !root->next[c] )
            root->next[c] = new Trie;
        
        root = root->next[c];
    }

    root->end = true;
}

void show ( Trie *node ) {
    for (int idx = 0; idx < 27; idx++ ) {
        if (node->next[idx]) {
            cout << (char) (idx + 'a');
            show(node->next[idx]);
        }
    }

    if (node->end) puts("");
}

int main ( void ) {
    string str;
    Trie trie;

    while ( cin >> str ) {
        insert(&trie, str);
    }
 
    show(&trie);

    return 0;
}