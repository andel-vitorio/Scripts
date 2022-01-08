M = int(input())
D = list(map(int, input().split()))

ze = -1
cd = -1
cze = -1

for idx in range(0, M):
    if D[idx] == 0 and ze == -1: ze = idx
    elif D[idx] == 5: cd = idx
    if (D[idx] == 0 or D[idx] == 5) and cze == -1: cze = idx


if cze == -1:
    -1
else:
    if D[M - 1] < 5:
        if ze != -1:
            D[ze], D[M - 1] = D[M - 1], D[ze]
        else: D[cd], D[M - 1] = D[M - 1], D[cd]
    else:
        D[cze], D[M - 1], D[M - 1], D[cze]

print(" ". join(list(map(str, D))))


