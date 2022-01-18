import math

B = int(input())
G = int(input())

qt = math.floor(G / 2)

if qt - B <= 0:
    print('Amelia tem todas bolinhas!')
else:
    print(f'Faltam %d bolinha(s)' % (qt - B))
