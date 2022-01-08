#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define hash(a, b)   (a % b)
#define rehash(a, b) ((a + 1) % b)
#define FAIL -1

typedef enum {
    false, true
} bool;

typedef struct Data {
    char name[25];
    int orig;
    int id;
} Data;

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
    int h = hash(data.id, hashTable->size);
    data.orig = h;
    
    while (hashTable->table[h] != NULL) {
        h = rehash(h, hashTable->size);
    }
    
    Node *node = (Node *)calloc(1, sizeof(Node));
    node->data = data;
    
    hashTable->table[h] = node;
}

int searchHashTable (HashTable *hashTable, int key) {
    int h = hash(key, hashTable->size);
    int k = 0;

    while (hashTable->table[h] != NULL && k != hashTable->size) {
       if (hashTable->table[h]->data.id == key)
           return h;
       else h = rehash(h, hashTable->size);
		 k++;
    }

    return FAIL;
}

void removeHashTable (HashTable *hashTable, int key) {
    int pos = searchHashTable(hashTable, key);
    
    if (pos != FAIL) {
        bool flag = false;
        while (true) {
            int idx = pos + 1;
            for (; idx < hashTable->size; idx++) {
                if (hashTable->table[idx] == NULL) {
                    hashTable->table[pos] = NULL;
                    flag = true;
                    break;
                }
                
                if (hashTable->table[idx]->data.orig <= pos) {
                    Node *tmp = hashTable->table[idx]; 
                    hashTable->table[pos] = tmp; 
                    hashTable->table[idx] = NULL;
                    pos = idx;
                    
                    break;
                }
            }
            
            if (flag == true || idx == hashTable->size)
                break;
        }
    }
}

void showHashTable (HashTable *hashTable) {
    Node *node;
    for (int pos = 0; pos < hashTable->size; pos++) {
        node = hashTable->table[pos];
        printf("%d ", pos);
        if (node == NULL) puts("\\");
        else printf("%d %d\n", node->data.id, node->data.orig);
    }
}

Data newData (char name[], int id) {
    Data data;
    data.id = id;
    strcpy(data.name, name);
    return data;
}


int main (void) {
    int N, M, value;
    char name[25];
    
    scanf("%d %d", &N, &M);
    
	HashTable *hashTable = newHashTable(N);
	
	while (M-- && scanf("%d %s%*c", &value, name)) {
	    insertHashTable(hashTable, newData(name, value));
	}
	
	showHashTable(hashTable);
	
	scanf("%d", &N);
	while (N-- && scanf("%d", &value)) {
    	removeHashTable(hashTable, value);
	} puts("");
	
	showHashTable(hashTable);
	
	puts(""); scanf("%d", &N);
	while (N-- && scanf("%d", &value)) {
    	if (searchHashTable(hashTable, value) == FAIL)
    	    puts("NAO");
    	else puts("SIM");
	}
	return 0;
}