#include <bits/stdc++.h>

using namespace std;

class Graph {
    int nVertex;
    list<int> *adjList;
    vector<int> *values;
    bool *visited;

    public:
        Graph (int vertex);
        void addEdge (int src, int dest);
        void bfs (int start);
        void dijkstra (int origem, int destino);
};

// Create a Graph
Graph::Graph (int vertex) {
    nVertex = vertex;
    adjList = new list<int>[vertex];
}

// Add edges
void Graph::addEdge (int src, int dest) {
    adjList[src].push_back(dest);
    adjList[dest].push_back(src);
}

// BFS algorithm
void Graph::bfs (int start) {
    visited = new bool[nVertex];

    for (int i = 0; i < nVertex; i++)
        visited[i] = false;

    list <int> queue;

    visited[start] = true;
    queue.push_back(start);

    list<int>::iterator it;

    while (!queue.empty()) {
        int current = queue.front();
        cout << "Visisted " << current << '\n';
        queue.pop_front();

        for (it = adjList[current].begin(); it != adjList[current].end(); ++it) {
            int adjVertex = *it;
            if (!visited[adjVertex]) {
                visited[adjVertex] = true;
                queue.push_back(adjVertex);
            }
        }
    }
}

// dijkstra algorithm
int dijkstra (int origem, int destino) {
    values = new vector<int>[nVertex];
	values[origem] = 0;

    queue <int> q;
	q.push(origem);

	while (!q.empty()) {
		int i = q.front();
		q.pop();

		for (int j = 0; j < n; j++) {
			if (graph[i][j] != INT_MAX && values[j] > values[i] + graph[i][j]) {
				values[j] = values[i] + graph[i][j];
				q.push(j);
			}
		}
	}
    
	return values[destino];
}

void INF (void) {
	for (int i = 0; i <= n; i++) {
		valor[i]= INT_MAX;
		for (int j=0; j <= n; j++) graph[i][j] = INT_MAX;
	}
}

int main (void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, s, e;

    cin >> N;
    Graph graph(N);

    for (int idx = 0; idx < N; idx++) {
        cin >> s >> e;
        graph.addEdge(s, e);
    }

    int node;
    cin >> node;
    graph.bfs(node);
    
    return 0;
}