#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define MAXS 10000
#define INFINITY INT_MAX

int graph[MAXS][MAXS], value[MAXS];
int N, conexoes, origem, destino;
int U, V, P;

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

	if (!q->head)
		q->head = aux;
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

void infinity (void)
{
	int idx = 0, idy = 0;
	
	for (; idx <= N; idx++)
	{
		value[idx] = INFINITY;

		for (idy = 0; idy <= N; idy++)
			graph[idx][idy] = INFINITY;
	}	
}

int dijkstra (int origem, int destino)
{
	value[origem] = 0;

	queue *q = newQueue();
	push(q, origem);

	while (q->size)
	{
		int idx = pop(q), idy = 0;

		for (; idy < N; idy++)
		{
			if (graph[idx][idy] != INFINITY && value[idy] > value[idx] + graph[idx][idy])
			{
				value[idy] = value[idx] + graph[idx][idy];
				push(q, idy);
			}
		}
	}
	
	return value[destino];
}

int main (void)
{
	scanf("%d %d %d %d", &N, &conexoes, &origem, &destino);

	int idx = 0; infinity();
	
	for (; idx < conexoes; idx++)
	{
		scanf("%d %d %d", &U, &V, &P);
		graph[U][V] = P;
	}

	printf("%d\n", dijkstra(origem, destino));
	return 0;
}