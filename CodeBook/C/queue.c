#include <stdio.h>
#include <stdlib.h>

typedef struct list
{
	int value;
	struct list *next;
} list;

typedef struct queue
{
	list *head;
	list *tail;
	size_t size;
} queue;

queue *newQueue (void)
{
	queue *q = (queue *)malloc(sizeof(queue));
	q->head = NULL;
	q->size = 0;
	return q;
}

void push (queue *q, int value)
{
	list *aux = (list *)malloc(sizeof(list));
	aux->value = value;
	aux->next = NULL;

	if (!q->head) q->head = aux;
	else q->tail->next = aux;

	q->tail = aux;
	q->size++;

	return;
}

int pop (queue *q)
{
	list *aux = q->head;
	int ans = aux->value;

	q->head = aux->next;
	q->size--;

	free(aux);
	return ans; 
}

int main (void)
{
	queue *q = newQueue();

	push(q, 10);
	push(q, 20);
	push(q, 30);
	push(q, 40);
	push(q, 50);

	while (q->size)
	{
		int ans = pop(q);
		printf("%d\n", ans);
	}

	return 0;
}