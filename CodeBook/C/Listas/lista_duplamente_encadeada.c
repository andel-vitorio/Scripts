#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
    false, true
} bool;

typedef int Data;

typedef struct Node {
    Data data;
    struct Node *prev;
    struct Node *next;
} Node;

typedef struct List {
    Node *start;
    Node *end;
    size_t size;
} List;

void init (List *list) {
    list->end = NULL;
    list->start = NULL;
    list->size = 0;
}

void insert (List *list, Data data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->prev = list->start;
    node->next = list->end;
    node->data = data;

    list->start = node;
    list->end = node;
    list->size++;
}

void insert_start (List *list, Data data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->prev = list->start;
    node->next = list->end;
    node->data = data;
    
    list->start->prev = node;
    list->start = node;
    list->size++;
}

void insert_end (List *list, Data data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->prev = list->end;
    node->next = NULL;
    node->data = data;
    
    list->end->next = node;
    list->end = node;
    list->size++;
}

void insert_prev (List *list, Data data, int pos) {
    Node *node = (Node *)malloc(sizeof(Node));
    Node *current = list->start;

    node->data = data;

    for (int idx = 1; idx < pos; idx++)
        current = current->next;
    node->next = current;
    node->prev = current->prev;
    
    if (!current->prev) 
        list->start = node;
    else current->prev->next = node; 

    current->prev = node;
    list->size++;
}

void insert_next (List *list, Data data, int pos) {
    Node *node = (Node *)malloc(sizeof(Node));
    Node *current = list->start;

    node->data = data;

    for (int idx = 1; idx < pos; idx++)
        current = current->next;
    node->next = current->next;
    node->prev = current;
    
    if (!current->next) 
        list->end = node;
    else current->next->prev = node; 

    current->next = node;
    list->size++;
}

void remove_node (List *list, int pos) {
    Node *node, *current;

    if (!list->size)
        return;

    if (pos == 1) {
        node = list->start;
        list->start = list->start->next;

        if (!list->start)
            list->end = NULL;
        else list->start->prev = NULL;
    } else if (pos == list->size) {
        node = list->end;
        list->end->prev->next = NULL;
        list->end = list->end->prev;
    } else {
        current = list->start;
        for (int idx = 1; idx < pos; idx++)
            current = current->next;
        node = current;
        current->prev->next = current->next;
        current->next->prev = current->prev;
    }

    free(node->data);
    free(node);
    list->size--;
}

void show (List list) {
    Node *node = list.start;
    while (node) {
        printf("%d ", node->data);
        node = node->next;
    } puts("");
}