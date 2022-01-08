#include <stdio.h>
#include <stdlib.h>

typedef enum {
    false, true
} bool;

typedef int typeData;

typedef struct typeNo {
  typeData data;
  struct typeNo *prox;
} typeNo;

typedef struct typeList {
  size_t size;
  typeNo *first;  
} typeList; 

void createList (typeList *list) {
  list->first = NULL;
  list->size = 0;
}

void insert_start (typeList *list, typeData newData) {
    typeNo *newNo = (typeNo *)malloc(sizeof(typeNo));
    newNo->data = newData;
    newNo->prox = NULL;
    newNo->prox = list->first;
    list->first = newData;
    list->size++;
}

void showList (typeList list) {
    printf("List:\n");
    while (list.first) {
        printf("%d\t", list.first->data);
        list.first = list.first->prox;
    }
    puts("");
}

bool isEmpty (typeList *list) { return list == NULL; }

typeNo *searchData (typeList list, typeData data) {
    typeNo *currentData = list.first;
    while (currentData) {
        if (currentData->data == data)
            return currentData;
        else currentData = currentData->prox;
    }
    return NULL;
}

typeList *removeNo (typeList *list, typeData data) {
    typeNo *currentNo = list->first;
    typeNo *lastNo = NULL;
    
    while (currentNo != NULL && currentNo->data != data) {
        lastNo = currentNo;
        currentNo = currentNo->prox;
    }

    if (currentNo == NULL)
        return NULL;

    if (currentNo == list->first) list->first = list->first->prox;
    else lastNo->prox = currentNo->prox;

    currentNo->prox = NULL;
    free(currentNo); 
    list->size--; 
    return list;
}

int main (void) {
    typeList list;
    createList(&list);
  return 0;
}