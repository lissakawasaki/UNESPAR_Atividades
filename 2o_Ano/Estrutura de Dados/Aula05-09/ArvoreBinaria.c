#include <stdio.h>
#include <stdlib.h>

// Árvore Binária

// Definindo o nó na árvore
typedef struct no {
    int conteudo;
    struct no *esquerda, *direita;
} No;

// Estrutura da Árvore Binária
typedef struct {
    No *raiz;
} ArvB;

// Função para inserir valor na Árvore Binária
void inserir(No **raiz, int valor) {
    if (*raiz == NULL) {
        No *novo = (No*)malloc(sizeof(No));
        novo->conteudo = valor;
        novo->esquerda = NULL;
        novo->direita = NULL;
        *raiz = novo;
    } else {
        if (valor < (*raiz)->conteudo) {
            inserir(&((*raiz)->esquerda), valor);
        } else if (valor > (*raiz)->conteudo) {
            inserir(&((*raiz)->direita), valor);
        }
    }
}

// Função para imprimir a árvore em ordem
void imprimirOrdem(No *raiz) {
    if (raiz != NULL) {
        imprimirOrdem(raiz->esquerda);
        printf("%d  ", raiz->conteudo);
        imprimirOrdem(raiz->direita);
    }
}

// Função para imprimir a árvore em pré-ordem
void imprimirPreOrdem(No *raiz) {
    if (raiz != NULL) {
        printf("%d  ", raiz->conteudo);
        imprimirPreOrdem(raiz->esquerda);
        imprimirPreOrdem(raiz->direita);
    }
}

// Função para imprimir a árvore em pós-ordem
void imprimirPosOrdem(No *raiz){
    if (raiz != NULL) {
        imprimirPosOrdem(raiz->esquerda);
        imprimirPosOrdem(raiz->direita);
        printf("%d  ", raiz->conteudo);
    }
}

// Função para calcular o tamanho da Árvore
int tamanho(No *raiz) {
    if (raiz == NULL) {
        return 0;
    } else {
        return 1 + tamanho(raiz->esquerda) + tamanho(raiz->direita);
    }
}

// Função para contar números pares na árvore
int contarPares(No *raiz) {
    if (raiz == NULL) {
        return 0;
    }

    int cont = 0;
    if (raiz->conteudo % 2 == 0) {
        cont = 1;
    }

    return cont + contarPares(raiz->esquerda) + contarPares(raiz->direita);
}

// Função para remover um valor da árvore
No* remover(No *raiz, int chave){
    if(raiz == NULL){
        printf("Valor não encontrado.\n");
        return NULL;
    } else {
        if(raiz->conteudo == chave){
            if(raiz->esquerda == NULL && raiz->direita == NULL){
                free(raiz);
                printf("Elemento folha removido: %d!\n", chave);
                return NULL;
            } else if(raiz->esquerda != NULL && raiz->direita != NULL) {
                No* aux = raiz->esquerda;
                while(aux->direita != NULL)
                    aux = aux->direita;
                raiz->conteudo = aux->conteudo;
                aux->conteudo = chave;
                printf("Elemento trocado: %d!\n", chave);
                raiz->esquerda = remover(raiz->esquerda, chave);
                return raiz;
            } else {
                No *aux;
                if (raiz->esquerda != NULL){
                    aux = raiz->esquerda;
                } else {
                    aux = raiz->direita;
                }
                free(raiz);
                printf("Elemento com 1 filho removido: %d!\n", chave);
                return aux;
            }
        } else {
            if (chave < raiz->conteudo){
                raiz->esquerda = remover(raiz->esquerda, chave);
            } else {
                raiz->direita = remover(raiz->direita, chave);
            }
            return raiz;
        }
    }
}

int main() {
    int op1, op2, valor;
    ArvB arv;
    arv.raiz = NULL;

    do {
        printf("\n0 - Sair\n1 - Inserir\n2 - Imprimir\n3 - Remover\n4 - Mostrar Pares\n");
        scanf("%d", &op1);

        switch (op1) {
            case 0: 
                printf("\nSaindo...\n");
                break;

            case 1:
                printf("Digite um valor: ");
                scanf("%d", &valor);
                inserir(&(arv.raiz), valor);
                break;

            case 2:
                printf("\nImpressao da Arvore Binaria: \n");
                printf("Qual tipo de impressao deseja realizar?\n");
                printf("\n1 - Em Ordem\n2 - Pre-Ordem\n3 - Pos-Ordem\n");
                scanf("%d", &op2);

                if (op2 == 1) {
                    printf("===================\n");
                    imprimirOrdem(arv.raiz);
                    printf("\n===================\n");
                } else if (op2 == 2) {
                    printf("\n===================\n");
                    imprimirPreOrdem(arv.raiz);
                    printf("\n===================\n");
                } else if (op2 == 3) {
                    printf("\n===================\n");
                    imprimirPosOrdem(arv.raiz);
                    printf("\n===================\n");
                } else {
                    printf("\n===================\n");
                    printf("Opção Invalida.\n");
                    printf("\n===================\n");
                }
                break;

            case 3:
                printf("\nImpressao da Arvore Binaria (Em Ordem):\n");
                imprimirOrdem(arv.raiz);
                printf("\nDigite o valor a ser removido: ");
                scanf("%d", &valor);
                arv.raiz = remover(arv.raiz, valor);
                break;

            case 4:
                printf("Tamanho: %d \n", tamanho(arv.raiz));
                printf("Numero de pares: %d", contarPares(arv.raiz));
                printf("\n===================\n");
                break;

            default:
                printf("Opção Inválida!\n");
        }
    } while (op1 != 0);
    
    return 0;
}
