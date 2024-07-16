import java.util.ArrayList;
import java.util.Scanner;
// Variáveis de instância

public class MaquinaDeTuring {
    private ArrayList<String> estados;
    private ArrayList<Character> simbolos;
    private ArrayList<Character> simbolosFita;
    private String estadoInicial;
    private ArrayList<String> estadosFinais;
    private Transicao[][] funcaoTransicao;
    private Character marcadorInicio;

    // Construtor para inicializar a máquina de Turing com os valores fornecidos
    public MaquinaDeTuring(ArrayList<String> estados, ArrayList<Character> simbolos, ArrayList<Character> simbolosFita, String estadoInicial, ArrayList<String> estadosFinais, Transicao[][] funcaoTransicao, Character marcadorInicio) {
        this.estados = estados;
        this.simbolos = simbolos;
        this.simbolosFita = simbolosFita;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.funcaoTransicao = funcaoTransicao;
        this.marcadorInicio = marcadorInicio;
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

             // Verifique se os índices são válidos
            if (indexEstado == -1 || indexSimbolo == -1) {
                System.out.println("Palavra não aceita! Estado ou símbolo inválido.");
                palavraAceita = false; // Estado ou símbolo não encontrado, palavra não aceita
                break;
            }


                if (transicao == null) {
                    System.out.println("Palavra não aceita! Transição inválida.");
                    palavraAceita = false; // Transição inválida, palavra não aceita
                    break;
                }

                System.out.println("Estado atual: " + estadoAtual);
                System.out.println("Símbolo atual: " + simboloAtual);
                System.out.println("Fita: " + fita.toString());
                System.out.println("Próxima transição: (" + estadoAtual + "," + simboloAtual + ") -> (" + transicao.getProximoEstado() + "," + transicao.getsimboloFita() + "," + transicao.getDirecao() + ")");
                System.out.println("--------------------------------");

                fita.escreverSimbolo(transicao.getsimboloFita()); // Escreve o símbolo na fita
                fita.moverCabeca(transicao.getDirecao()); // Move a cabeça de leitura/escrita
                estadoAtual = transicao.getProximoEstado(); // Atualiza o estado atual

                if (estadosFinais.contains(estadoAtual)) {
                    System.out.println("Palavra aceita!\n"); // Verifica se o estado atual é um estado final
                    System.out.println("Fita inicio: ");
                    System.out.printf("%c%s%c\n", marcadorInicio, entradaUsuario, marcadorInicio);
                    
                    System.out.println("Fita final: ");
                    System.out.printf("%c%s\n", marcadorInicio, fita.toString());

    
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
    public static void main(String[] args) {

        System.out.println("--- Linguagens Formais, Autômatos e Computabilidade ---\n  ");
        System.out.println("\t  Lissa Guirau Kawasaki\n");
        System.out.println("\t--- Máquina de Turing --- \n");

        Scanner scanner = new Scanner(System.in);

        // Leitura do estado inicial
        System.out.print("Informe o estado inicial: ");
        String estadoInicial = scanner.nextLine().trim();

        // Leitura dos estados finais
        System.out.print("Informe os estados finais (separados por espaço): ");
        ArrayList<String> estadosFinais = new ArrayList<>();
        for (String estado : scanner.nextLine().split(" ")) {
            estadosFinais.add(estado);
        }

        // Leitura dos estados
        System.out.print("Informe o conjunto de estados (separados por espaço): ");
        ArrayList<String> estados = new ArrayList<>();
        for (String estado : scanner.nextLine().split(" ")) {
            estados.add(estado);
        }

        // Leitura dos símbolos de entrada
        System.out.print("Informe os símbolos de entrada (separados por espaço): ");
        ArrayList<Character> simbolos = new ArrayList<>();
        for (String simbolo : scanner.nextLine().split(" ")) {
            simbolos.add(simbolo.charAt(0));
        }

        // Leitura dos símbolos da fita
        System.out.print("Informe os símbolos da fita, incluindo o simbolo branco. (separados por espaço): ");
        ArrayList<Character> simbolosFita = new ArrayList<>();
        for (String simbolo : scanner.nextLine().split(" ")) {
            simbolosFita.add(simbolo.charAt(0));
        }

        System.out.print("Informe o marcador de início: ");
        char marcadorInicio = scanner.nextLine().charAt(0);

        char simboloBranco = simbolosFita.get(simbolosFita.size() - 1);

        // Inicialização da função de transição
        Transicao[][] funcaoTransicao = new Transicao[estados.size()][simbolosFita.size()];

        // Leitura das transições
        System.out.println("Preencha as transições:");
        for (String estado : estados) {
            for (Character simbolo : simbolosFita) {
                System.out.printf(" δ (%s,%c): ", estado, simbolo);
                String transicao = scanner.nextLine().trim();
                if (transicao.equalsIgnoreCase("X")) {
                    funcaoTransicao[estados.indexOf(estado)][simbolosFita.indexOf(simbolo)] = null;
                } else {
                    String[] partes = transicao.split(",");
                    String proximoEstado = partes[0];
                    char simboloFita = partes[1].charAt(0);
                    char direcao = partes[2].charAt(0);
                    funcaoTransicao[estados.indexOf(estado)][simbolosFita.indexOf(simbolo)] = new Transicao(proximoEstado, simboloFita, direcao);
                }
            }
        }

        // Criação da máquina de Turing e execução
        MaquinaDeTuring mt = new MaquinaDeTuring(estados, simbolos, simbolosFita, estadoInicial, estadosFinais, funcaoTransicao, marcadorInicio);
        mt.executarMT();
    }
}
