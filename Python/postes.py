N = int(input())
X = map(int, input().split())

n1 = 0
n2 = 0

for x in X:
    if x < 50:
        n1 += 1
    elif x < 85:
        n2 += 1

print(n1, n2)
