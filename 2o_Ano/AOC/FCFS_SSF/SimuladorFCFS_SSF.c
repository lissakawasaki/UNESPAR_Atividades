#include <stdio.h>
#include <string.h>
#include <locale.h>

void FCFS(int vetorPedidos[], char acessoCilindro[], int tamanhoCilindro, int qntdPedidos) {
    printf("\n==========================================================\n");
    printf("INÍCIO DO ESCALONAMENTO (FIRST COME FIRST SERVED) \n");
    printf("==========================================================\n");

    for (int i = 0; i < qntdPedidos; i++) {
        acessoCilindro[vetorPedidos[i] - 1] = 'XX';

        printf("\nRepresentação do Cilindro:\n");
        printf("----------------------------------------------------------\n");

        printf("|| ");
        for (int j = 0; j < tamanhoCilindro; j++) {
            printf("%2d || ", j + 1);
        }

        printf("\n|| ");
        for (int j = 0; j < tamanhoCilindro; j++) {
            printf("%2c || ", acessoCilindro[j]);
        }

        printf("\n----------------------------------------------------------\n");
    }

    printf("FINAL DO ESCALONAMENTO (FIRST COME FIRST SERVED) \n");
    printf("==========================================================\n");
}

void SSF(int vetorPedidos[], char acessoCilindro[], int tamanhoCilindro, int qntdPedidos) {
    printf("\n==========================================================\n");
    printf("INÍCIO DO ESCALONAMENTO (SHORTEST SEEK FIRST) \n");
    printf("==========================================================\n");

    int atendidos[qntdPedidos];
    for (int i = 0; i < qntdPedidos; i++) {
        atendidos[i] = 0;
    }

    int posicaoAtual = vetorPedidos[0];
    int cont = 0;

    while (cont < qntdPedidos) {
        int menorDistanciaPedido = -1;
        int menorDistancia = tamanhoCilindro;

        for (int i = 0; i < qntdPedidos; i++) {
            if (atendidos[i]) continue;

            int distancia = vetorPedidos[i] - posicaoAtual;
            if (distancia < 0) {
                distancia *= -1;
            }
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                menorDistanciaPedido = i;
            }
        }

        if (menorDistanciaPedido != -1) {
            posicaoAtual = vetorPedidos[menorDistanciaPedido];
            acessoCilindro[posicaoAtual - 1] = 'XX';
            atendidos[menorDistanciaPedido] = 1;
            cont++;

            printf("\nRepresentação do Cilindro:\n");
            printf("----------------------------------------------------------\n");

            printf("|| ");
            for (int j = 0; j < tamanhoCilindro; j++) {
                printf("%2d || ", j + 1);
            }

            printf("\n|| ");
            for (int j = 0; j < tamanhoCilindro; j++) {
                printf("%2c || ", acessoCilindro[j]);
            }

            printf("\n----------------------------------------------------------\n");
        }
    }

    printf("FINAL DO ESCALONAMENTO (SHORTEST SEEK FIRST) \n");
    printf("==========================================================\n");
}

int main() {
    setlocale(LC_ALL, "Portuguese");

    int tamanhoCilindro;
    printf("Informe o tamanho do cilindro: ");
    scanf("%d", &tamanhoCilindro);

    int qntdPedidos;
    printf("Informe a quantidade de pedidos de cilindros: ");
    scanf("%d", &qntdPedidos);

    int vetorPedidos[qntdPedidos];
    for (int i = 0; i < qntdPedidos; i++) {
        printf("Informe o pedido %d: ", i + 1);
        scanf("%d", &vetorPedidos[i]);
    }

    printf("\n==========================================================\n");
    printf("Representação inicial do Cilindro:\n");
    printf("----------------------------------------------------------\n");
    printf("|| ");
    for (int i = 0; i < tamanhoCilindro; i++) {
        printf("%2d || ", i + 1);
    }

    char acessoCilindro[tamanhoCilindro];
    for (int i = 0; i < tamanhoCilindro; i++){
        acessoCilindro[i] = '--';
    }
        printf("\n|| ");
        for (int i = 0; i < tamanhoCilindro; i++){
            printf("%2c || ", acessoCilindro[i]);
        }

    printf("\n==========================================================\n");
    printf("\n Vetor de Pedidos");
    printf("\n==========================================================\n");
    printf("||");
    for(int i = 0; i < qntdPedidos; i++){
        printf(" %2d ||", vetorPedidos[i]);
    }

    printf("\n");
    FCFS(vetorPedidos, acessoCilindro, tamanhoCilindro, qntdPedidos);

    // Reseta o vetor acessoCilindro para o algoritmo SSF
    for (int i = 0; i < tamanhoCilindro; i++){
        acessoCilindro[i] = '--';
    }

    SSF(vetorPedidos, acessoCilindro, tamanhoCilindro, qntdPedidos);
}

