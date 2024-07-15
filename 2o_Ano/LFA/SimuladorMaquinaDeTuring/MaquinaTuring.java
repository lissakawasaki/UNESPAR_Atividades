import java.util.*;

public class MaquinaTuring {

    static class Transicao {
        private final String estadoAtual;
        private final String simboloAtual;
        private final String proximoEstado;
        private final String simboloParaEscrever;
        private final String direcao;

        public Transicao(String estadoAtual, String simboloAtual, String proximoEstado, String simboloParaEscrever, String direcao) {
            this.estadoAtual = estadoAtual;
            this.simboloAtual = simboloAtual;
            this.proximoEstado = proximoEstado;
            this.simboloParaEscrever = simboloParaEscrever;
            this.direcao = direcao;
        }

        public String getEstadoAtual() {
            return estadoAtual;
        }

        public String getSimboloAtual() {
            return simboloAtual;
        }

        public String getProximoEstado() {
            return proximoEstado;
        }

        public String getSimboloParaEscrever() {
            return simboloParaEscrever;
        }

        public String getDirecao() {
            return direcao;
        }
    }

    private void executarMT(List<String> estados, List<String> simbolos, List<String> simbolosFita, List<Transicao> funcaoTransicao, String estadoInicial, List<String> estadosFinais, char marcadorInicio, Scanner scanner) {
        while (true) {
            System.out.print("Insira a palavra a ser verificada: ");
            String entradaUsuario = scanner.nextLine().trim().replaceAll("\\s", ""); // Remove espaços em branco
            char[] entradaUsuarioArray = entradaUsuario.toCharArray();

            boolean palavraValida = true;
            for (char c : entradaUsuarioArray) {
                if (!simbolos.contains(String.valueOf(c))) {
                    palavraValida = false;
                    break;
                }
            }

            if (!palavraValida) {
                System.out.println("Palavra não aceita! Símbolos inválidos.");
                continue;
            }

            List<String> fita = new ArrayList<>();
            fita.add(String.valueOf(marcadorInicio));
            for (char c : entradaUsuarioArray) {
                fita.add(String.valueOf(c));
            }

            int posicaoCabeca = 1;
            String estadoAtual = estadoInicial;
            boolean palavraAceita = false;

            while (true) {
                if (posicaoCabeca >= fita.size()) {
                    fita.add(" ");
                } else if (posicaoCabeca < 0) {
                    fita.add(0, " ");
                    posicaoCabeca = 0;
                }

                String simboloAtual = fita.get(posicaoCabeca);
                Transicao transicao = encontrarTransicao(funcaoTransicao, estadoAtual, simboloAtual);

                if (transicao == null) {
                    System.out.println("Palavra não aceita! Transição inválida.");
                    break;
                }

                String proximoEstado = transicao.getProximoEstado();
                String simboloParaEscrever = transicao.getSimboloParaEscrever();
                String direcao = transicao.getDirecao();

                fita.set(posicaoCabeca, simboloParaEscrever);
                if (direcao.equals("R")) {
                    posicaoCabeca++;
                } else if (direcao.equals("L")) {
                    posicaoCabeca--;
                }

                estadoAtual = proximoEstado;

                if (estadosFinais.contains(estadoAtual)) {
                    System.out.println("Palavra aceita!");
                    palavraAceita = true;
                    break;
                }

                if (Math.abs(posicaoCabeca) > 1000) {
                    System.out.println("Palavra não aceita! Ultrapassou o limite definido.");
                    break;
                }
            }

            if (!palavraAceita) {
                System.out.println("Palavra não aceita!");
            }
        }
    }

    private Transicao encontrarTransicao(List<Transicao> funcaoTransicao, String estadoAtual, String simboloAtual) {
        for (Transicao t : funcaoTransicao) {
            if (t.getEstadoAtual().equals(estadoAtual) && t.getSimboloAtual().equals(simboloAtual)) {
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o estado inicial: ");
        String estadoInicial = sc.nextLine().trim();

        System.out.print("Informe os estados finais (separados por espaço): ");
        String[] estadosFinaisArray = sc.nextLine().split("\\s+");
        List<String> estadosFinais = Arrays.asList(estadosFinaisArray);

        System.out.print("Informe o conjunto de estados (separados por espaço): ");
        String[] estadosArray = sc.nextLine().split("\\s+");
        List<String> estados = Arrays.asList(estadosArray);

        System.out.print("Informe os símbolos de entrada (separados por espaço): ");
        String[] simbolosArray = sc.nextLine().split("\\s+");
        List<String> simbolos = Arrays.asList(simbolosArray);

        System.out.print("Informe os símbolos da fita (separados por espaço, incluindo o símbolo branco): ");
        String[] simbolosFitaArray = sc.nextLine().split("\\s+");
        List<String> simbolosFita = Arrays.asList(simbolosFitaArray);

        System.out.print("Informe o marcador de início: ");
        char marcadorInicio = sc.nextLine().charAt(0);

        List<Transicao> funcaoTransicao = new ArrayList<>();

        System.out.println("Preencha as transições:");
        for (String estado : estados) {
            for (String simbolo : simbolosFita) {
                System.out.print(" δ (" + estado + "," + simbolo + "): ");
                String transicao = sc.nextLine().trim();

                if (!transicao.equals("X")) {
                    String[] transicaoAtual = transicao.split(",");
                    String proximoEstado = transicaoAtual[0];
                    String simboloParaEscrever = transicaoAtual[1];
                    String direcao = transicaoAtual[2];
                    funcaoTransicao.add(new Transicao(estado, simbolo, proximoEstado, simboloParaEscrever, direcao));
                }
            }
        }

        MaquinaTuring mt = new MaquinaTuring();
        mt.executarMT(estados, simbolos, simbolosFita, funcaoTransicao, estadoInicial, estadosFinais, marcadorInicio, sc);
        sc.close();
    }
}
