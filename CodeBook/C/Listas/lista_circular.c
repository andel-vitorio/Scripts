#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
    false, true
} bool;

typedef struct Data {
    char name[80];
    size_t duration;
} Process;

typedef struct Node {
    Process process;
    struct Node *prev;
    struct Node *next;
} Node;

typedef struct List {
    Node *start;
    Node *end;
    size_t size;
} List;

void init (List *list) {
    list->start = NULL;
    list->end = NULL;
    list->size = 0;
}

void insert (List *list, Process process) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->process = process;

    if (!list->start) {
        node->next = node;
        list->start = node;
        list->end = node;
    } else if (list->size == 1) {
        list->start->prev = node;
        list->start->next = node;
        list->end = node;

        node->prev = list->start;
        node->next = list->start;
    } else {
        list->start->prev = node;
        
        node->next = list->start;
        node->prev = list->end;
        
        list->end->next = node;
        list->end = node;
    }
    
    list->size++;
}

void delete (List *list, int pos) {
    Node *node, *current;

    if (list->size == 0)
        return;

    if (pos == 1) {
        node = list->start;
        list->start = list->start->next;

        if (!list->start) 
            list->end = NULL;
        else {
            list->end->next = list->start;
            list->start->prev = list->end;
        }

	} else if (pos == list->size) {
        node = list->end;
        list->end->prev->next = list->start;
        list->end = list->end->prev;
        list->start->prev = list->end->prev;
    } else {
        current = list->start;
        for (int idx = 1; idx < pos; idx++) 
            current = current->next;
        node = current;
        current->prev->next = current->next;
        current->next->prev = current->prev;
    }

    free(node);
	list->size--;
}

void show (List *list) {
    int N = 5;
    Node *node = list->start;
    while (N--) {
        printf("%d ", node->process.duration);
        node = node->next;
    } puts("");
}

void solve (List *list, int temp) {
    Node *current;
    int n = 545, d;
    int pos = 1, total = 0;

    while (list->size) {
        current = list->start;
        pos = 1;

        while (current->next != list->start) {
            int c = current->process.duration;
            if (c <= temp) {
                total += c;
                printf("%d us: %s finalizou\n", total, current->process.name);
                delete(list, pos);
            } else {
                current->process.duration -= temp;
                pos++, total += temp;
            }
            
            current = current->next;
        }

        int c = current->process.duration;
        if (c <= temp) {
            total += c;
            printf("%d us: %s finalizou\n", total, current->process.name);
            delete(list, pos);
        } else {
            current->process.duration -= temp;
            pos++, total += temp;
        }    
    }
}

int main (void) {
    Process process;
    List list;
    int Q, N;

    init(&list);

    scanf("%d %d", &Q, &N);

    for (int idx = 0; idx < N; idx++) {
        scanf("%s %d%*c", process.name, &process.duration);
        insert(&list, process);
    }

    solve(&list, Q);

    return 0;
}