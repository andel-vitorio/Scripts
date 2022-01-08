#include <bits/stdc++.h>
#include <windows.h>

using namespace std;

LARGE_INTEGER tempoInicial, tempoFinal, freq; // Tempo em Clocks
double tempoTotal; // Tempo em Segundos
int Cn = 0; // Número de Comparações
int Mn = 0; // Número de Movimentações

void Merge (vector <int> &v, vector <int> &aux, int start, int middle, int end) {
    int left = start, right = middle;

    for (int idx = start; idx < end; idx++) {
        if (left < middle and (right >= end or (++Cn and v[left] > v[right]))) {
            aux[idx] = v[left];
            left++;
        } else {
            aux[idx] = v[right];
            right++;
        }
    }

    for (int idx = start; idx < end; idx++) {
        v[idx] = aux[idx], Mn++;
    }
}

void MergeSort (vector <int> &v, vector <int> &aux, int start, int end) {
    if (end - start < 2)
        return;

    int middle = (int) (start + end) / 2;
    MergeSort(v, aux, start, middle);
    MergeSort(v, aux, middle, end);
    Merge(v, aux, start, middle, end);
}

void mergeSort (vector <int> &v) {
    vector <int> aux(v.size());
    MergeSort(v, aux, 0, v.size());
}

void showVector (vector <int> sequencia) {
    for (int &elemento: sequencia) {
        cout << elemento << "\n";
    } puts("");
}


int main (void) {
    vector <int> sequencia;
    int elemento;

    while (cin >> elemento)
        sequencia.push_back(elemento);

    QueryPerformanceCounter(&tempoInicial);
    mergeSort(sequencia);
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