#include <stdio.h>
#include <stdlib.h>

#define MAX 10
#define FAIL -1

typedef enum bool {
    false, true
} bool;

typedef int typeData;

typedef struct typeList {
    typeData *datas;
    size_t size;
} typeList;

void init (typeList *list) {
    list->datas = (int *)malloc(MAX * sizeof(typeData));
    list->size = 0;
}

size_t length (typeList list) { return list.size; }

typeData get (typeList list, int index) {
    if (index < 0 || index >= index)
        return FAIL;
    else list.datas[index];
}

int search (typeList list, typeData data) {
    for (int idx = 0; idx < list.size; idx++)
        if (list.datas[idx] == data)
            return idx;
    return FAIL;
}

void show (typeList list) {
    for (int idx = 0; idx < list.size; idx++)
        printf("%d\t", list.datas[idx]);
    puts("");
}

bool insert_start (typeList *list, typeData data) {
    if (list->size == MAX)
        return false;
    
    for (int idx = list->size; idx > 0; idx--) 
        list->datas[idx] = list->datas[idx - 1];

    list->datas[0] = data;
    list->size++;

    return true;    
}

bool insert_end (typeList *list, typeData data) {
    if (list->size == MAX)
        return false;
    
    list->datas[list->size] = data;
    list->size++;
    
    return true;
}

bool insert_before (typeList *list, typeData key, typeData data) {
    if (list->size == MAX)
        return false;

    int index_key = search(*list, key);
    
    if (index_key == FAIL)
        return false;

    for (int idx = list->size; idx > index_key; idx --) 
        list->datas[idx] = list->datas[idx - 1];

    list->datas[index_key] = data;
    list->size++;

    return true;
}

bool insert_after (typeList *list, typeData key, typeData data) {
    if (list->size == MAX)
        return false;

    int index_key = search(*list, key);
    
    if (index_key == FAIL)
        return false;

    for (int idx = list->size; idx > index_key + 1; idx --) 
        list->datas[idx] = list->datas[idx - 1];

    list->datas[index_key + 1] = data;
    list->size++;

    return true;
}

int main (void) {
    return 0;
}