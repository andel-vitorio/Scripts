#include <bits/stdc++.h>

using namespace std;

#define MAXS 100005

int N, A, B, C;
int visited[MAXS], aux[MAXS], dist1, dist2;
int lista[MAXS];
vector<int> adjacent[MAXS];

void dfs (int u)
{
	visited[u] = 1;

	for (int v : adjacent[u])
		if (!visited[v])
			aux[v] = u, dfs(v);
}

int main (void)
{	
	while (~scanf("%d %d %d %d", &N, &A, &B, &C))
	{
		memset(visited, 0, sizeof(visited));
		memset(lista, 0, sizeof(lista));

		for (int idx = 0; idx <= N; idx++)
			adjacent[idx].clear();

		int u, v;

		for (int idx = 0; idx < N - 1; idx++)
		{
			scanf("%d %d", &u, &v);
			adjacent[u].push_back(v);
			adjacent[v].push_back(u);
		}

		aux[A] = -1;
		dfs(A);

		u = B;
		dist1 = 0;

		while (~u)
		{
			dist1++;
			lista[u] = dist1;
			u = aux[u];
		}

		u = C;
		dist2 = 0;

		while (!lista[u])
		{
			dist2++; 
			u = aux[u];
		}

		dist1 = lista[u] - 1;
		
		printf("%.6f\n", (double) dist2 / (dist2 + dist1));
	}

	return 0;
}