#include <bits/stdc++.h>
#include <windows.h>

using namespace std;

LARGE_INTEGER tempoInicial, tempoFinal, freq; // Tempo em Clocks
double tempoTotal; // Tempo em Segundos
int Cn = 0; // Número de Comparações
int Mn = 0; // Número de Movimentações

void bubbleSort (vector <int> &v) { 
    for (int idx = 0; idx < v.size(); idx++) {
        for (int idy = idx + 1; idy < v.size(); idy++) {
            if (++Cn and v[idx] > v[idy]) { // Processo de Comparação
                swap(v[idx], v[idy]);
                 Mn++; // Processo de Movimentação
            }
        }
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
    bubbleSort(sequencia);
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