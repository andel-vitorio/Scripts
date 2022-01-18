#include <stdio.h>

int main() {
    int dia, mes, ano;
    scanf("%i %i %i", &dia, &mes, &ano);
    if(dia <= 0 || dia >= 32)
        printf("ta errada lezo\n");
    else if(mes <= 0 || mes >= 13)
        printf("ta errada lezo\n");    
    else if(ano <= 0 || ano >= 2025)
     printf("ta errada lezo\n");
    else
        printf("%02d/%02d/%04d\n", dia, mes, ano);
   // if(mes >0  && mes < 13)
 
    return 0;
}