#include <stdio.h>

typedef struct {
    int dia, mes, ano;
}Data;

typedef struct {
    char nome[50];
    Data data;
}Pessoa;

typedef struct no {
    Pessoa p;
    struct no *proximo;
}No;

typedef struct{
    No *topo;
    int tamanho;
}Pilha;

Pessoa ler_pessoa(){
    Pessoa p;
    printf("\n Digite o nome e a data de nascimento (Formato dd/mm/aaaa): ");
   scanf("%d49[^\n]%d%d%d", p.nome, &p.data.dia, &p.data.mes, &p.data.ano);
}

void imprimir_pessoa(Pessoa p){
    printf("\n Nome: %s \n Data: %2d/%2d/%4d \n", p.nome, p.data.dia, p.data.mes, p.data.ano);
}

void criar_pilha(Pilha *p){
    p->topo = NULL;
    p->tamanho = 0;
}

void empilhar(Pilha *p){
    No *novo = malloc(sizeof(No));
    if (novo){
        novo->p = ler_pessoa();
        novo->proximo = p->topo;
        p->topo = novo;
        p->tamanho++;
    } else {
        pritf("\n Erro ao alocar memória... \n");
    }

No* desempilhar(Pilha *p){
    if(p->topo){
        No *remover = p->topo;
        p->topo = remover->proximo;
        p->tamanho--;
        return remover;
    }
    else
        printf("\nPilha vazia!\n");
    return NULL;
}

void imprimir_pilha(Pilha *p){
    No *aux = p->topo;
    printf("\n----------- PILHA Tam: %d --------------\n", p->tam);
    while(aux){
        imprimir_pessoa(aux->p);
        aux = aux->proximo;
    }
    printf("\n--------- FIM PILHA ------------\n");
}

int main(){

    No *remover;
    Pilha p;
    int opcao;

    criar_pilha(&p);

        printf("\n0 - Sair\n1 - Empilhar\n2 - Desempilhar\n3 - Imprimir\n");
        scanf("%d", &opcao);
        getchar();

        switch(opcao){
        case 1:
            empilhar(&p);
            break;
        case 2:
            remover = desempilhar(&p);
            if(remover){
                printf("\nElemento removido com sucesso!\n");
                imprimir_pessoa(remover->p);
                free(remover);
            }
            else{
                printf("\nSem no a remover.\n");
            }
            break;
        case 3:
            imprimir_pilha(&p);
            break;
        default:
            if(opcao != 0)
                printf("\nOpcao invalida!!!\n");
        }

    return 0;
}


