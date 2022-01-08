valores = [] # lista que contera os valores numericos
size = 5 # tamanho da lista

for count in range(0, size): #laco onde 'count' vai de 0 a (size - 1)
    print(f'Digite um valor para a posição {count}: ', end = '')
    num = int(input()) # ler os valores inteiros linhas por linha
    valores.append(num) # adiciona o valor lido na lista

menor = min(valores) # retorna o menor valor da lista
maior = max(valores) # retorna o maior valor da lista

print(f'Você digitou os valores: {valores}') # o 'f' permite voce escrever as variaveis dentro da mensagem desta forma {variavel}
print(f'O maior valor digitado foi {maior} nas posições: ', end = '') # end = (char) define o ultimo caracter da mensagem
for pos in range(0, size): #laco onde 'pos' vai de 0 a (size - 1)
    if valores[pos] == menor: # conpara os valores com o menor, se for igual, entra no bloco condicional
        print(pos, '... ', end = '') # printa as posicoes onde se encontra os valores menores
print() # pula a linha

print(f'Você digitou os valores: {valores}') # o 'f' permite voce escrever as variaveis dentro da mensagem desta forma {variavel}
print(f'O maior valor digitado foi {maior} nas posições: ', end = '') # end = (char) define o ultimo caracter da mensagem
for pos in range(0, size): #laco onde 'pos' vai de 0 a (size - 1)
    if valores[pos] == maior: # conpara os valores com o maior, se for igual, entra no bloco condicional
        print(pos, '... ', end = '') # printa as posicoes onde se encontra os valores maiores
print() # pula a linhajava
