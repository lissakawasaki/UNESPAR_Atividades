import java.util.*;

class MaquinaDeTuring {

    // Classe que representa uma transição na Máquina de Turing
    class Transicao {
        char lerSimbolo;  // Símbolo que a máquina lê da fita
        char novoSimbolo;  // Símbolo que a máquina escreve na fita
        int estadoAtual;  // Estado atual da máquina
        int proxEstado;  // Próximo estado da máquina após a transição
        char direcao;  // Direção para mover a cabeça ('L' para esquerda, 'R' para direita)

        // Construtor da classe Transicao para inicializar os atributos
        Transicao(int estadoAtual, char lerSimbolo, int proxEstado, char novoSimbolo, char direcao) {
            this.estadoAtual = estadoAtual;
            this.lerSimbolo = lerSimbolo;
            this.proxEstado = proxEstado;
            this.novoSimbolo = novoSimbolo;
            this.direcao = direcao;
        }
    }

    int estadoInicial;  // Estado inicial da máquina de Turing
    int estadoAceitacao;  // Estado de aceitação da máquina de Turing

    // Lista para armazenar todas as transições da máquina de Turing
    List<Transicao> transicoes = new ArrayList<>();

    // Método para ler as transições a partir da entrada do usuário
    void lerTransicoes() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira a quantidade de transições: ");
        int qntdTransicoes = sc.nextInt();  // Lê o número de transições a serem inseridas

        for (int i = 0; i < qntdTransicoes; i++) {
            System.out.printf("=== Transição %d ===\n", i + 1);  // Indica qual transição está sendo inserida

            // Lê o estado inicial da transição
            System.out.print("Estado inicial: ");
            int estadoAtual = sc.nextInt();

            // Lê o símbolo que a máquina deve ler na fita
            System.out.print("Símbolo: ");
            char lerSimbolo = sc.next().charAt(0);

            // Lê o próximo estado para onde a máquina deve ir após a transição
            System.out.print("Próximo estado: ");
            int proxEstado = sc.nextInt();

            // Lê o novo símbolo a ser escrito na fita
            System.out.print("Novo símbolo a ser escrito na fita: ");
            char novoSimbolo = sc.next().charAt(0);

            // Lê a direção para a qual a cabeça deve se mover ('L' ou 'R')
            System.out.print("Direção ('L' para esquerda, 'R' para direita): ");
            char direcao = sc.next().charAt(0);

            // Adiciona a nova transição à lista de transições
            transicoes.add(new Transicao(estadoAtual, lerSimbolo, proxEstado, novoSimbolo, direcao));
        }
    }

    // Inicializa a fita com espaços em branco e carrega o conteúdo inicial fornecido pelo usuário
    void inicializarFita(char[] fita, String entrada) {
        Arrays.fill(fita, ' ');  // Preenche a fita com espaços em branco
        for (int i = 0; i < entrada.length(); i++) {
            fita[i] = entrada.charAt(i);  // Copia o conteúdo da entrada para a fita
        }
    }

    // Encontra a transição correspondente ao estado atual e ao símbolo lido na fita
    Transicao encontrarTransicao(int estadoAtual, char simboloLido) {
        for (Transicao t : transicoes) {
            if (t.estadoAtual == estadoAtual && t.lerSimbolo == simboloLido) {
                return t;  // Retorna a transição encontrada que corresponde ao estado e símbolo atuais
            }
        }
        return null;  // Retorna null se não encontrar uma transição válida
    }

    // Simula a Máquina de Turing com base nas transições e na fita fornecida
    void simularMaquinaDeTuring(char[] fita) {
        int estadoAtual = estadoInicial;  // Define o estado inicial da máquina de Turing
        int posicaoCabeca = 0;  // Define a posição inicial da cabeça de leitura na fita
    
        // Loop que continua até a máquina de Turing alcançar o estado de aceitação
        while (estadoAtual != estadoAceitacao) {
            // Obtém o símbolo atual da fita na posição da cabeça
            char simboloLido = fita[posicaoCabeca];
            
            // Encontra a transição correspondente ao estado atual e ao símbolo lido
            Transicao transicao = encontrarTransicao(estadoAtual, simboloLido);

            // Se não houver uma transição válida, encerra a simulação e exibe uma mensagem de erro
            if (transicao == null) {
                System.out.println("Nenhuma transição válida encontrada.");
                return;
            }

            // Atualiza o símbolo na fita com base na transição
            fita[posicaoCabeca] = transicao.novoSimbolo;
            estadoAtual = transicao.proxEstado;  // Atualiza o estado atual para o próximo estado definido pela transição

            // Move a cabeça da fita de acordo com a direção definida na transição
            if (transicao.direcao == 'R') {
                posicaoCabeca++;  // Move a cabeça para a direita
            } else if (transicao.direcao == 'L') {
                posicaoCabeca--;  // Move a cabeça para a esquerda
            } else {
                System.out.println("Direção inválida.");
                return;  // Encerra a simulação se a direção for inválida
            }

            // Verifica se a cabeça está fora dos limites da fita e encerra a simulação se necessário
            if (posicaoCabeca < 0 || posicaoCabeca >= fita.length) {
                System.out.println("Limite inválido.");
                return;
            }
        }

        // Se a máquina de Turing alcançou o estado de aceitação, exibe uma mensagem de sucesso
        System.out.println("Máquina de Turing alcançou o estado de aceitação.");
    }

    // Método principal para executar a Máquina de Turing
    public static void main(String[] args) {

        MaquinaDeTuring mt = new MaquinaDeTuring();  // Cria uma nova instância da Máquina de Turing
        char[] fita = new char[100];  // Inicializa a fita com o tamanho definido

        Scanner sc = new Scanner(System.in);

        // Lê o estado inicial da máquina de Turing
        System.out.print("Digite o estado inicial: ");
        mt.estadoInicial = sc.nextInt();

        // Lê o estado de aceitação da máquina de Turing
        System.out.print("Digite o estado de aceitação: ");
        mt.estadoAceitacao = sc.nextInt();
        sc.nextLine();  // Limpa o buffer de entrada

        // Lê as transições da máquina de Turing
        mt.lerTransicoes();  

        System.out.print("Digite a palavra a ser verificada: ");
        String conteudoFita = sc.nextLine();

        // Inicializa a fita com o conteúdo fornecido
        mt.inicializarFita(fita, conteudoFita);

        // Executa a simulação da Máquina de Turing
        mt.simularMaquinaDeTuring(fita);

        // Exibe o conteúdo final da fita após a simulação
        System.out.println("Conteúdo final da fita: " + new String(fita).trim());
    }
}
