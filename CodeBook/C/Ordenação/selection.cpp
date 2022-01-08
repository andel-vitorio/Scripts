#include <bits/stdc++.h>
#include <windows.h>

using namespace std;

LARGE_INTEGER tempoInicial, tempoFinal, freq; // Tempo em Clocks
double tempoTotal; // Tempo em Segundos
int Cn = 0; // Número de Comparações
int Mn = 0; // Número de Movimentações

void selection (vector <int> &array) {
    for (int i = 0; i < array.size() - 1; i++) {
        int minor = i;

        for (int j = i + 1; j < array.size(); j++) {
            if (++Cn and array[j] < array[minor]) {
                minor = j;
            }
        } 

        if (minor == i)
            continue;

        int aux = array[minor];
        array[minor] = array[i], ++Mn;
        array[i] = aux;
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
    selection(sequencia);
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