#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define hash(a, b) (a % b)
#define FAIL -1

typedef enum {
  false, true
} bool;

typedef struct Data {
    int value;
} Data;

typedef struct Node {
	Data data;
    struct Node *prox;
} Node;

typedef struct List {
  int size;
  Node *first;  
} List;

typedef struct HashTable {
	int size;
	List **table;
} HashTable;

List *newList (void) {
    List *list = (List *)malloc(sizeof(List));
    list->first = NULL;
    list->size = 0;
    return list;
}

HashTable *newHashTable (int size) {
	HashTable *hashTable = (HashTable *)malloc(sizeof(HashTable));
    hashTable->table = (List **)malloc(size * sizeof(List *));
	hashTable->size = size;

	for (int idx = 0; idx < hashTable->size; idx++)
		hashTable->table[idx] = NULL;
	
	return hashTable;
}

Node *insertList (Node *node, Data data) {
    if (node == NULL) {
        node = (Node *)malloc(sizeof(Node));
        node->data = data;
        node->prox = NULL;
    } else {
        node->prox = insertList(node->prox, data);
        return node;
    }
}

void insertHashTable (HashTable *hashTable, Data data) {
    int h = hash(data.value, hashTable->size);

    if (hashTable->table[h] == NULL)
        hashTable->table[h] = newList();

    List *aux = hashTable->table[h];
    aux->first = insertList(aux->first, data);
    aux->size++;
}

void showList (Node *node) {
    if (node == NULL) {
      puts(" -> \\");
      return;
    }

    printf(" -> %d", node->data.value);

    showList(node->prox);
}

void showHashTable (HashTable *hashTable) {
    List *list = NULL;
    
    for (int pos = 0; pos < hashTable->size; pos++) {
        printf("%d", pos);
        list = hashTable->table[pos];    
        if (list == NULL) puts(" -> \\");
        else showList(list->first);
    }
}

void solve ( int m, int n ) {
  HashTable *hashTable = newHashTable(m);
  Data data;

  for ( int i = 0; i < n; i++ ) {
    scanf("%d", &data.value);
    insertHashTable(hashTable, data);
  }

  showHashTable(hashTable);
}

int main (void) {
  int testes, m, n;
  bool flag = false;

  scanf("%d", &testes);

  while ( testes-- && scanf("%d %d", &m, &n) ) {
    if ( flag ) {
      puts("");
    }

    flag = true;

    solve(m, n);
  }
	
	return 0;
}