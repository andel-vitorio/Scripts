#include <stdio.h>
#include <stdlib.h>

struct arvgen 
{
  char info;
  struct arvgen *prim;
  struct arvgen *prox;
};
typedef struct arvgen ArvGen;

ArvGen* cria (char c)
{
  ArvGen* a =(ArvGen*)malloc(sizeof(ArvGen));
  a->info = c;
  a->prim = NULL;
  a->prox = NULL;
  return a;
}

void insere(ArvGen* a, ArvGen* f)
{
  f->prox = a->prim;
  a->prim = f;
}

void imprime(ArvGen* a)
{
  ArvGen* p;
  printf("%c\n", a->info);
  for (p = a->prim; p != NULL; p = p->prox)
    imprime(p);
}

int busca(ArvGen* a, char c)
{
  ArvGen* p;
  if (a->info == c)
    return 1;
  else
  {
    for (p = a->prim; p != NULL; p = p->prox)
    {
      if (busca(p, c))
        return 1;
    }
  }
  return 0;
}
void libera(ArvGen* a)
{
  ArvGen* p = a->prim;
  while (p != NULL)
  {
    ArvGen* t = p->prox;
    libera(p);
    p = t;
  }
  free(a);
}


/*
Node *search (Node *node, Data data) {
    Data aux = node->data;
    if (data == NULL) return NULL;
    else if (cmp(data, aux) > 0) return search(node->left, data);
    else if (cmp(data, aux) < 0) return search(node->right, data);
    else return aux;
}

    if (child == NULL) child = new;
    else child->next = insert(child->next, new);
    return child;
}

bool search (Node *node, Node *new, Data data) {
    Node *aux;

    if (node->data == data) {
        node->first = insert(node->first, new);
        return true;
    }
    else {
        for (aux = node->first; aux != NULL; aux = aux->next)
            if (search(aux, new, data)) 
                return true;
    }

    return false;
}*/

ArvGen* cria_exemplo(void)
{
  /* cria nós como folhas */
  ArvGen *a = cria('a');
  ArvGen *b = cria('b');
  ArvGen *c = cria('c');
  ArvGen *d = cria('d');
  ArvGen *e = cria('e');
  ArvGen *f = cria('f');
  ArvGen *g = cria('g');
  ArvGen *h = cria('h');
  ArvGen *i = cria('i');
  ArvGen *j = cria('j');
  /* monta a hierarquia */
  insere(c, d);
  insere(b, e);
  insere(b, c);
  insere(i, j);
  insere(g, i);
  insere(g, h);
  insere(a, g);
  insere(a, f);
  insere(a, b);
  return a;
}

int main()
{
  printf("Criando exemplo do slide...\n");
  ArvGen* exemplo = cria_exemplo();
  
  printf("Mostrando a árvore...\n");
  imprime(exemplo);
  libera(exemplo);

  return 0;
}