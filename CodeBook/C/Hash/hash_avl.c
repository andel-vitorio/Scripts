#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define hash(a, b)   (a % b)
#define FAIL -1

typedef enum {
    false, true
} bool;

typedef struct Data {
    char name[21];
    int  id;
} Data;

typedef struct Node {
	Data data;
    struct Node *left;
    struct Node *right;
} Node;

typedef struct HashTable {
	int size;
	Node **table;
} HashTable;

Data newData (char name[], int id) {
    Data data;
    strcpy(data.name, name);
    data.id = id;
    return data;
}

Node *newNode (Data data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->data = data;
    node->left = NULL;
    node->right = NULL;
    return node;
}

int getHeight (Node *root) {
    if (!root) return 0;
    else {
        if (getHeight(root->left) > getHeight(root->right)) 
            return (getHeight(root->left) + 1);
        else return (getHeight(root->right) + 1);
    }
}

int getBF (Node *node) {
    if (node) return (getHeight(node->left) - getHeight(node->right));
    else return 0;
}

Node *RL (Node *node) {
    Node *aux = node->right;
    node->right = aux->left;
    aux->left = node;
    return aux;
}

Node *RR (Node *node) {
    Node *aux = node->left;
    node->left = aux->right;
    aux->right = node;
    return aux;
}

Node *balance (Node *node) {
    int bf = getBF(node);
    Node *aux;

    if (bf > 1) {
        aux = node->left;
        bf = getBF(aux);

        if (bf < 0) node->left = RL(node->left); // Rotação Dupla L-R
        node = RR(node); // Rotação Simples Direita
    } else if (bf < -1) {
        aux = node->right;
        bf = getBF(aux);

        if (bf > 0) node->right = RR(node->right); // Rotação Dupla R-L
        node = RL(node); //Rotação Simples Esquerda
    }

    return node;
}

Node *insertNode (Node *root, Data data) {
    if (root == NULL) {
        root = newNode(data);
        return root;
    } else {
        Data tmp = root->data;
        if (data.id < root->data.id)
            root->left = insertNode(root->left, data);
        else root->right = insertNode(root->right, data);
    }

    return balance(root);
}

bool isFirst = true;
void showNode (Node *node) {
    if (node) {
        showNode(node->left);
        if (isFirst) printf("%d %s", node->data.id, node->data.name);
        else printf(" %d %s", node->data.id, node->data.name);
        isFirst = false;
        showNode(node->right);
    }
}

HashTable *newHashTable (int size) {
	HashTable *hashTable = (HashTable *)malloc(sizeof(HashTable));
    hashTable->table = (Node **)malloc(size * sizeof(Node *));
	hashTable->size = size;

	for (int idx = 0; idx < hashTable->size; idx++)
		hashTable->table[idx] = NULL;
	
	return hashTable;
}

void insertHashTable (HashTable *hashTable, Data data) {
    int h = hash(data.id, hashTable->size);
    hashTable->table[h] = insertNode(hashTable->table[h], data);
}

void showHashTable (HashTable *hashTable) {
    Node *node = NULL;
    
    for (int pos = 0; pos < hashTable->size; pos++) {
        printf("%d ", pos); isFirst = true;
        node = hashTable->table[pos];    
        if (node == NULL) printf("null\n");
        else showNode(node), puts("");
    }
}

Data *searchNode (Node *node, int id) {
    if (node == NULL) return NULL;
    int current = node->data.id;
    
    if (current == id) {
        Data *data = (&node->data);
        return data;
    } else if (id > current) return searchNode(node->right, id); 
    else return searchNode(node->left, id); 
}

Data *searchHashTable (int *pos, HashTable *hashTable, int id) {
    int h = hash(id, hashTable->size);
    Node *node = hashTable->table[h];
    
    if (node == NULL) return NULL;
    else {
        Data *data = searchNode(node, id);
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