#include <bits/stdc++.h>
#include <windows.h>

using namespace std;

LARGE_INTEGER tempoInicial, tempoFinal, freq; // Tempo em Clocks
double tempoTotal; // Tempo em Segundos
int Cn = 0; // Número de Comparações
int Mn = 0; // Número de Movimentações

void heapSort (vector <int> &v) {
    int idx = (int) v.size() / 2;
    int parent, child, tmp;
    int index = v.size();

    while (true) {
        if (idx > 0) {
            idx--;
            tmp = v[idx];
        } else {
            index--;
            if (index <= 0)
                return;

            tmp = v[index];
            v[index] = v[0], Mn++;
        }

        parent = idx;
        child = idx * 2 + 1;

        while (child < index) {
            if (child + 1 < index && (++Cn and v[child + 1] > v[child])) 
                child++;
            if (++Cn and v[child] > tmp) {
                v[parent] = v[child], Mn++;
                parent = child;
                child = parent * 2 + 1;
            } else break;
        }

        v[parent] = tmp, Mn++;
    }
}

void showVector (vector <int> sequencia) {
    for (int &elemento: sequencia) {
        cout << elemento << " ";
    } puts("");
}

int main (void) {
    vector <int> sequencia;
    int elemento;

    while (cin >> elemento)
        sequencia.push_back(elemento);
    
    QueryPerformanceCounter(&tempoInicial);
    heapSort(sequencia);
    QueryPerformanceCounter(&tempoFinal);
    QueryPerformanceFrequency(&freq);
    tempoTotal = (double)(tempoFinal.QuadPart - tempoInicial.QuadPart);
    tempoTotal /= (double) freq.QuadPart;

    showVector(sequencia);

    cout << "Tempo de Processamento: " << tempoTotal << "s" << endl;
    cout << "N. Movimentação: " << Mn << endl;
    cout << "N. Comparações: " << Cn << endl;

    return 0;
}