#include <bits/stdc++.h>
#include <windows.h>

using namespace std;

LARGE_INTEGER tempoInicial, tempoFinal, freq; // Tempo em Clocks
double tempoTotal; // Tempo em Segundos
int Cn = 0; // Número de Comparações
int Mn = 0; // Número de Movimentações

void insertion (vector <int> &arr) {
    for (int i = 1; i < arr.size(); i++) {
        int tmp = arr[i];
        int j = i - 1;

        while ( tmp < arr[j] and j >= 0 and ++Cn) {
            arr[j + 1] = arr[j], Mn++;
            j--;
        }

        arr[j + 1] = tmp, Mn++;
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
    insertion(sequencia);
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