#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int num;
    struct No *prox;
}no;

    no *criar_no() {
        no *novo = (no*)malloc(sizeof(no));
        return novo;
    }

    no *inserir_no_inicio(no *lista, int dado) {
        no *novo_no = criar_no();
        novo_no -> num = dado;

        if(lista == NULL) {
            lista = novo_no;
            novo_no -> prox = NULL;
        } else {
            novo_no -> prox = lista;
            lista = novo_no;
        }
    return lista;
    }

    no *inserirElementoFim(no *lista, int dado){

        no *novoFim = criar_no();
        novoFim -> num = dado;

        if(lista == NULL){
            lista = novoFim;
            novoFim -> prox = NULL;
        } else {
            no *aux = lista;
        while(aux -> prox != NULL){
            aux = aux -> prox;
        }

        novoFim -> prox = NULL;
        aux -> prox = novoFim;
        }

        return lista;

    }

void imprimir_lista(no *lista){
    no *aux = lista;
    while(aux != NULL){
        printf("%d ", aux -> num);
        aux = aux -> prox;
    }
}

    no *remover(no *lista,int dado){
        if (lista == NULL) return NULL;
        no *temp = lista;
        no *prev = NULL;

        if (temp != NULL && temp -> num == dado){
            lista = temp -> prox;
            free(temp);
            return lista;
        }
        while(temp != NULL && temp->num != dado){
            prev = temp;
            temp = temp -> prox;
        }
        if (temp == NULL) return lista;
        prev -> prox = temp -> prox;
            free(temp);
            return lista;
    }



int main(){

    no *lista = NULL;

    lista = inserir_no_inicio(lista, 10);
    lista = inserir_no_inicio(lista, 20);
    lista = inserirElementoFim(lista, 30);
    imprimir_lista(lista);
    printf("\n");

    int numRemover;
    printf("Digite o número da lista que deseja remover: ");
    scanf("%d",&numRemover);

    lista = remover(lista, numRemover);
    imprimir_lista(lista);
}