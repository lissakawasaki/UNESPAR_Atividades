#include <stdio.h>
#include <stdlib.h> 
#include <stdbool.h>

// Implementação de fila

typedef struct no {
    int valor;
    struct no *proximo;
} No; 

typedef struct {
    No *inicio;
    No *final;
    int tamanho;
} Fila;

void criarFila(Fila *fila){ // Criando uma fila
    fila->inicio = NULL;
    fila->final = NULL;
    fila->tamanho = 0;
}

void inserirFila(Fila *fila, int num){
    No *novo = malloc (sizeof(No));
        if(novo == NULL){
            novo->valor = num;
            novo->proximo = NULL;
            if (fila->inicio == NULL){
                fila->inicio = novo;
                fila->final = novo;
            } else {
                fila->final->proximo = novo;
                fila->final = novo;
            }
                fila->tamanho++;

        } else {
            printf("\n Erro ao alocar memória.");
        }
    }

No* removerFila(Fila *fila){
    No *remover = NULL;
    if(fila->inicio){
        remover = fila->inicio;
        fila->inicio = remover -> proximo;
        fila->tamanho--;
    } else {
        printf("\t Fila Vazia! \n");
        return remover;
    }
}    

void imprimirFila(Fila *fila){
    No *aux = fila->inicio;
    printf("\t ----FILA---- \n");
        while(aux = true){
            printf("%d", aux->valor);
            aux = aux->proximo;
        }
    printf("\t ----FIM DA FILA---- \n");
}

int main(){
    No *r;
    Fila fila;
    criarFila(&fila); // Inicializa uma fila
    int valor, entradaUsuario;

    do {
        printf("0 - sair \n 1 - Inserir \n 2 - Remover \n 3 - Imprimir \n");
        scanf("%d0, &entradaUsuario");
        switch (entradaUsuario)
        {
        case 1:
        printf("Digite um valor a ser inserido na fila: ");
        scanf("%d", &valor);
        inserirFila(&fila, valor);
            break;
        case 2:
        printf("O valor indicado pelo indice sera removido.\n");
        r = removerFila(&fila);
        printf("Removido: %d\n", r->valor);
        free(r);
            break;
        case 3:
        imprimirFila(&fila);
            break;
        default:
            if(entradaUsuario != 0){
                printf("Opção inválida.");
            }
            break;
        }
    } while (entradaUsuario != 0);
        return 0;
}