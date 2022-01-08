#Biblioteca que permite trabalhar com datas
from datetime import date

# listas dos grupos
Junior = [] 
Regular = []
Master = []
Senior = []

print('Digite 10 datas de nascimento:')
for i in range(0, 10):
    data_nascimento = list(map(int, input('Data %.2d (DD/MM/AAAA): ' % (i + 1)).split('/'))) # ler as data de nascimentos no formato (DD/MM/AAAA)
    data_atual = list(map(int, str(date.today()).split('-'))) # ler a data atual no formato (AAAA/MM/DD)
    
    D1 = date(data_nascimento[2], data_nascimento[1], data_nascimento[0])
    D2 = date(data_atual[0], data_atual[1], data_atual[2])

    delta = D2 - D1 
    idade = int(delta.days / 365)
    
    if idade <= 20:
        Junior.append(idade)
    elif idade > 20 and idade <= 35:
        Regular.append(idade)
    elif idade > 35 and idade <= 50:
        Master.append(idade)
    elif idade > 50:
        Senior.append(idade)

print('\nQuantitativo em cada Grupo:')
print(f'Grupo 01 (Junior): {len(Junior)}')
print(f'Grupo 02 (Regular): {len(Regular)}')
print(f'Grupo 03 (Master): {len(Master)}')
print(f'Grupo 04 (Senior): {len(Senior)}')
    
