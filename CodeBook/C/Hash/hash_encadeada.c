#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define hash(a, b) (a % b)
#define FAIL -1

typedef struct Data {
    char name[21];
    int  id;
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

Data newData (char name[], int id) {
    Data data;
    strcpy(data.name, name);
    data.id = id;
    return data;
}

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
    int h = hash(data.id, hashTable->size);

    if (hashTable->table[h] == NULL)
        hashTable->table[h] = newList();

    List *aux = hashTable->table[h];
    aux->first = insertList(aux->first, data);
    aux->size++;
}

void showList (Node *node, int pos) {
    if (node == NULL) {return;}
    printf("%d %d %s\n", pos, node->data.id, node->data.name);
    showList(node->prox, pos);
}

void showHashTable (HashTable *hashTable) {
    List *list = NULL;
    
    for (int pos = 0; pos < hashTable->size; pos++) {
        list = hashTable->table[pos];    
        if (list == NULL) printf("%d null null\n", pos);
        else showList(list->first, pos);
    }
}

Data *searchList (Node *node, int id) {
    if (node == NULL) return NULL;
    else {
        Data *data = (&node->data);
        if (data->id == id) return data;
        else return searchList(node->prox, id);
    }
}

Data *searchHashTable (int *pos, HashTable *hashTable, int id) {
    int h = hash(id, hashTable->size);
    List *list = hashTable->table[h];
    
    if (list == NULL) return NULL;
    else {
        Data *data = searchList(list->first, id);
        *pos = ((data == NULL) ?FAIL :h);
        return data;
    }
}

int main (void) {
    int size, value;
    char name[21];
    Data data;
    
    scanf("%d", &size);
    
	HashTable *hashTable = newHashTable(size);
    
	while (scanf("%d", &value) && value) {
	    scanf("%s%*c", name);
	    data = newData(name, value);
	    insertHashTable(hashTable, data);
	}
	
	showHashTable(hashTable);
	
	puts("- - -");
	
	Data *result = NULL;
    int pos = 0;	

	while (scanf("%d", &value) && value) {
	    result = searchHashTable(&pos, hashTable, value);
	    if (pos != FAIL && result != NULL)
	        printf("%d %d %s\n", pos, result->id, result->name);
	    else printf("%d not found\n", value); 
	}
	       
	return 0;
}

/*

9
123456 Pedro
3209 Carlos
1358 Lizandro
12504 Mario
654326 Paulo
54219 Marcos
0
3209
654326
54219
1111
0
*/