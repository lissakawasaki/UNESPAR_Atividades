import java.util.ArrayList;
import java.util.Scanner;

public class MaquinaDeTuring {
    // Variáveis de instância para armazenar estados, símbolos, símbolo da fita, estado inicial, estados finais e função de transição
    private ArrayList<String> estados;
    private ArrayList<Character> simbolos;
    private ArrayList<Character> simbolosFita;
    private String estadoInicial;
    private ArrayList<String> estadosFinais;
    private Transicao[][] funcaoTransicao;

    // Construtor para inicializar a máquina de Turing com os valores fornecidos
    public MaquinaDeTuring(ArrayList<String> estados, ArrayList<Character> simbolos, ArrayList<Character> simbolosFita, String estadoInicial, ArrayList<String> estadosFinais, Transicao[][] funcaoTransicao) {
        this.estados = estados;
        this.simbolos = simbolos;
        this.simbolosFita = simbolosFita;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.funcaoTransicao = funcaoTransicao;
    }

    // Método para executar a máquina de Turing
    public void executarMT() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Insira a palavra a ser verificada: ");
            String entradaUsuario = scanner.nextLine(); // Lê a entrada do usuário
            Fita fita = new Fita(entradaUsuario); // Inicializa a fita com a palavra de entrada
            String estadoAtual = estadoInicial; // Define o estado inicial
            boolean palavraAceita = true; // Flag para verificar se a palavra foi aceita

            while (true) {
                char simboloAtual = fita.lerSimbolo(); // Lê o símbolo atual na fita
                int indexEstado = estados.indexOf(estadoAtual); // Obtém o índice do estado atual
                int indexSimbolo = simbolosFita.indexOf(simboloAtual); // Obtém o índice do símbolo atual
                Transicao transicao = funcaoTransicao[indexEstado][indexSimbolo]; // Obtém a transição correspondente

                if (transicao == null) {
                    System.out.println("Palavra não aceita! Transição inválida.");
                    palavraAceita = false; // Transição inválida, palavra não aceita
                    break;
                }

                System.out.println("Estado atual: " + estadoAtual);
                System.out.println("Símbolo atual: " + simboloAtual);
                System.out.println("Fita: " + fita);
                System.out.println("Próxima transição: (" + estadoAtual + "," + simboloAtual + ") -> (" + transicao.getProximoEstado() + ", " + transicao.getsimboloFita() + ", " + transicao.getDirecao() + ")");
                System.out.println("--------------------------------");

                fita.escreverSimbolo(transicao.getsimboloFita()); // Escreve o símbolo na fita
                fita.moverCabeca(transicao.getDirecao()); // Move a cabeça de leitura/escrita
                estadoAtual = transicao.getProximoEstado(); // Atualiza o estado atual

                if (estadosFinais.contains(estadoAtual)) {
                    System.out.println("Palavra aceita!"); // Verifica se o estado atual é um estado final
                    break;
                }

                if (!fita.posicaoValida()) {
                    System.out.println("Palavra não aceita! Fita fora dos limites.");
                    palavraAceita = false; // Verifica se a cabeça de leitura/escrita está dentro dos limites da fita
                    break;
                }
            }

            if (palavraAceita && !estadosFinais.contains(estadoAtual)) {
                System.out.println("Palavra não aceita!"); // Palavra não aceita se o estado final não foi alcançado
            }
        }
    }
}
