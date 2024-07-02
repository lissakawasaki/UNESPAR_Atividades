// Lista - dados ligados de maneira linear
//Ter ponteiros apontando para o início da lista
/*1 - criar menu da lista (impressão, ordenar, começo, fim, contagem)
2 - contagem de nós
3 - criar um método para fazer a média dos números inseridos nos nós*/

#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int num;
    struct No *prox; // Ponteiro que aponta para o próximo nó.
} no;

no *inserir_ordenado(no *lista, int dado);
void imprimir_lista(no *lista);
int contar_nos(no *lista);
float calcular_media(no *lista);
void liberar_lista(no *lista);

no *inserir_ordenado(no *lista, int dado){
    no novoNo = (no)malloc(sizeof(no)); // Reserva memoria para struct
    novoNo -> num = dado;
    novoNo -> prox = NULL;

    // Se lista igual a null ou num for menor que o total de dados
    if(lista == NULL || lista -> num >= dado){
        novoNo -> prox = lista; //
        lista = novoNo;
        return novoNo;
    }

    no *anterior = NULL;
    no *atual = lista;

    while(atual != NULL && atual -> num < dado){
        anterior = atual;
        atual = atual -> prox;
    }

    novoNo -> prox = atual;
    anterior = atual -> prox;
    return lista;

}

no *criar_no() { // Função para alocar memória dinamicamente.
    no novo = (no)malloc(sizeof(no)); // Alocando memória dinâmica para o ponteiro nó.
    return novo;
}

no *inserir_no_inicio(no *lista, int dado) { // Função para inserir no início da lista.
    no *novo_no = criar_no(); // Criar o novo nó.
    novo_no->num = dado; // Atribuindo um número (dado) ao nó.

    if (lista == NULL) { // Verificando se a lista está vazia.
        novo_no->prox = NULL; // Se a lista está vazia, o próximo nó será NULL.
    } else { // Lista não está vazia.
        novo_no->prox = lista; // O novo nó aponta para o início da lista atual.
    }
    lista = novo_no; // A lista agora aponta para o novo nó.
    return lista;
}

no *inserirElementoFim(no *lista, int dado) {
    no *novoFim = criar_no();
    novoFim->num = dado;

    if (lista == NULL) { // Lista vazia.
        lista = novoFim;
        novoFim->prox = NULL;
    } else {
        no *aux = lista;
        while (aux->prox != NULL) {
            aux = aux->prox;
        }
        novoFim->prox = NULL;
        aux->prox = novoFim;
    }

    return lista;
}

void imprimir_lista(no *lista) {
    no *aux = lista; // O auxiliar percorre a lista para que nenhum dado da lista seja perdido.
    while (aux != NULL) {
        printf("%d ", aux->num); // Imprimindo o número armazenado no nó.
        aux = aux->prox; // Avançando para o próximo nó.
    }
    printf("\n"); // Para adicionar uma nova linha após a impressão da lista.
}

void calcular_media(no *lista){
    no *aux = lista;

    media = 

}

no *remover(no *lista, int dado) {
    if (lista == NULL) { // Se a lista está vazia.
        return NULL;
    }

    no *temp = lista;
    no *prev = NULL;

    if (temp != NULL && temp->num == dado) { // Se o nó a ser removido é o primeiro.
        lista = temp->prox; // Atualizando o início da lista.
        free(temp); // Liberando a memória do nó removido.
        return lista;
    }

    while (temp != NULL && temp->num != dado) { // Procurando o nó a ser removido.
        prev = temp;
        temp = temp->prox;
    }

    if (temp == NULL) { // Se o nó não foi encontrado.
        return lista;
    }

    prev->prox = temp->prox; // Removendo o nó.
    free(temp); // Liberando a memória do nó removido.

    return lista;
}

int main() {

    int opcao, valor;
    no *lista = NULL; // Criando um ponteiro de lista que aponta para NULL.

    do {
        printf("\nMenu:\n");
        printf("1. Inserir ordenado\n");
        printf("2. Imprimir lista\n");
        printf("3. Contar nós\n");
        printf("4. Calcular média\n");
        printf("5. Sair\n");
        printf("Escolha uma opção: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                printf("Digite um valor para inserir: ");
                scanf("%d", &valor);
                lista = inserir_ordenado(lista, valor);
                break;
            case 2:
                imprimir_lista(lista);
                break;
            case 3:
                printf("Total de nós: %d\n", contar_nos(lista));
                break;
            case 4:
                printf("Média dos valores: %.2f\n", calcular_media(lista));
                break;
            case 5:
                printf("Saindo...\n");
                break;
            default:
                printf("Opção inválida!\n");
        }
    } while (opcao != 5);

    liberar_lista(lista);

    lista = inserir_ordenado(lista, 3);
    lista = inserir_ordenado(lista, 2);
    lista = inserir_ordenado(lista, 1);
    imprimir_lista(lista);


   /*lista = inserirElementoFim(lista, 10);
    lista = inserirElementoFim(lista, 20);
    lista = inserirElementoFim(lista, 30);
    imprimir_lista(lista); 

    printf("Qual numero deseja remover? \n");
    scanf("%d", &valor);
    lista = remover(lista, valor);
    imprimir_lista(lista);*/

    return 0;
}