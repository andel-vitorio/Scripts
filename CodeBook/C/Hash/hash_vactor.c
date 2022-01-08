#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define hash(a, b)   (a % b)
#define rehash(a, b) ((a + 1) % b)
#define FAIL -1

typedef int Data;

typedef struct Node {
	Data data;
} Node;

typedef struct HashTable {
	int size;
	Node **table;
} HashTable;

HashTable *newHashTable (int size) {
	HashTable *hashTable = (HashTable *)calloc(1, sizeof(HashTable));
    hashTable->size = size;
    hashTable->table = (Node **)calloc(size, sizeof(Node *));
		
	for( int idx = 0; idx < hashTable->size; idx++)
		hashTable->table[idx] = NULL;
	
	return hashTable;
}

void insertHashTable (HashTable *hashTable, Data data) {
    int h = hash(data, hashTable->size);
    
    while (hashTable->table[h] != NULL) {
        h = rehash(h, hashTable->size);
    }
    
    Node *node = (Node *)calloc(1, sizeof(Node));
    node->data = data;
    
    hashTable->table[h] = node;
}

int searchHashTable (HashTable *hashTable, Data key) {
    int h = hash(key, hashTable->size);
    int k = 0;

    while (hashTable->table[h] != NULL && k != hashTable->size) {
       if (hashTable->table[h]->data == key)
           return h;
       else h = rehash(h, hashTable->size);
		 k++;
    }

    return FAIL;
}

int main (void) {
    int size, value;
    
    scanf("%d", &size);
    
	HashTable *hashTable = newHashTable(size);
	
	while (scanf("%d", &value) && value) {
	    insertHashTable(hashTable, value);
	}
	
	while (scanf("%d", &value) && value) {
        int ans = searchHashTable(hashTable, value);
        printf("%d ", value);
        if (ans == FAIL) puts("not found");
        else printf("%d\n", ans);
    }
	        
	return 0;
}