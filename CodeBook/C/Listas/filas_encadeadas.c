#include <stdio.h>
#include <stdlib.h>

typedef enum {
    false, true
} bool;

typedef int Data;

typedef struct Node {
    Data data;
    struct Node *next;    
} Node;

typedef struct Queue {
    size_t size;
    Node *start;
    Node *end;
} Queue; 

void init_queue (Queue *queue) {
    queue->size = 0;
    queue->end = NULL;
    queue->start = NULL;
}

void enqueue (Queue *queue, Data data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->data = data;
    node->next = NULL;

    if (!queue->start) {
        queue->start = node;
        queue->end = node;
    } else {
        queue->end->next = node; 
        queue->end = node;
    }

    queue->size++;
}

Node *dequeue (Queue *queue) {
    if (queue->start) {
        Node *node = queue->start;
        queue->start = queue->start->next;
        node->next = NULL;

        if (queue->start) 
            queue->end = NULL;

        queue->size--;
        return node;
    } else return NULL;
}

void delete (Queue *queue) {
    Node *node = queue->start;

    while (node) {
        queue->start = queue->start->next;
        free(node);
        queue->size--;
        node = queue->start;
    }
}

void show (Queue queue) {
    while (queue.start) {
        printf("%d ", queue.start->data);
        queue.start = queue.start->next;
    } puts("");
}