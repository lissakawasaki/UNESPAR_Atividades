import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
        System.out.print("Informe os símbolos da fita (separados por espaço, incluindo o símbolo branco '_'): ");
        ArrayList<Character> simbolosFita = new ArrayList<>();
        for (String simbolo : scanner.nextLine().split(" ")) {
            simbolosFita.add(simbolo.charAt(0));
        }

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
        MaquinaDeTuring mt = new MaquinaDeTuring(estados, simbolos, simbolosFita, estadoInicial, estadosFinais, funcaoTransicao);
        mt.executarMT();
    }
}
