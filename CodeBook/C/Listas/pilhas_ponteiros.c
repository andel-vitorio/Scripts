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

typedef struct Stack {
    size_t size;
    Node *top;
} Stack;

void init_stack (Stack *stack) {
    stack->size = 0;
    stack->top = NULL;
}

void push (Stack *stack, Data data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->data = data;
    node->next = stack->top;
    stack->top = node;
    stack->size++; 
}

Node *pop (Stack *stack) {
    if (stack->top) {
        Node *node = stack->top;
        stack->top = stack->top->next;

        if (node->next) 
            node->next = NULL;
        
        stack->size--;
        return node;
    } else return NULL;
}

void show (Stack stack) {
    printf("Pilha: ");
    while (stack.top) {
        printf("|%d| ", stack.top->data);
        stack.top = stack.top->next;
    }
}