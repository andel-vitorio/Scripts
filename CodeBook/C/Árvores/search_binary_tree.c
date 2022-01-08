#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
    false, true
} bool;

typedef int Data;

typedef struct Node {
    Data data;
    struct Node *left;
    struct Node *right;
} Node;

int cmp (Data d1, Data d2) {return d2 - d1;}

Node *insert (Node *node, Data data) {
    if (node == NULL) {
        node = (Node *)malloc(sizeof(Node));
        node->data = data;
        node->left = NULL;
        node->right = NULL;
    } else {
        Data d = node->data; 
        if (cmp(data, d)) node->left = insert(node->left, data);
        else node->right = insert(node->right, data);
    }

    return node;
}

// INFIXADA - ordenada
void show (Node *node) {
    if (node == NULL)
        return;

    show(node->left);
    printf("%d\n", node->data);
    show(node->right);
}
