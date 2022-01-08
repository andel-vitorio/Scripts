//https://www.javatips.net/api/junrar-android-master/src/gnu/crypto/hash/RipeMD160.java
// https://md5calc.com/hash/ripemd160/1234567890qwertyuiopasdfghjklzxc

#include <bits/stdc++.h>
#include "RIPEMD160.h"

using namespace std;

unsigned char SHA256outB[64], SHA256outB2[32];
unsigned char RIPEMDout[20], RIPEMDout2[20];
unsigned char input[64];

void conversion (void)
{
    for (int idx = 0; idx < 64; idx+=2)
    {
        int hex;
        char aux[3];
        aux[0] = input[idx];
        aux[1] = input[idx + 1];
        aux[2] = '\0';

        sscanf(aux, "%x", &hex);

        SHA256outB[idx / 2] = hex;
    }
}

int main (void)
{
    //cin >> SHA256outB;
    unsigned char str[] = {0x41, 0x6e, 0x64, 0x65, 0x6c, 0x0a, 0x0d, 0x56, 0x69, 0x74, 0xa2, 0x72, 0x69, 0x6f, 0x0a, 0x0d};

    //conversion();

    /*for (int idx = 0; idx < 32; idx++)
    {
        int a = input[idx];
        printf("%x", a);
    } puts("");*/

    int out = ripemd160(str, 16, RIPEMDout);

    for (int idx = 0; idx < 20; idx++)
    {
        int a = RIPEMDout[idx];
        printf("%x", a);
    } puts("");
    
    return 0;
}