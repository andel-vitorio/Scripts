B = map(int, input().split())

S = [0 for s in range(0, 10)]

N = True

for bolas in B:
    S[bolas] += 1
    if S[bolas] > 4:
        N = False
        break

if N: print('S')
else: print('N')