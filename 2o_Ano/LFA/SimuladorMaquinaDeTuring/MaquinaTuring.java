import java.util.*;

public class MaquinaTuring {

    void executar_MT(estados, simbolos, simbolos_fita, funcao_Transicao, estado_inicial, estados_finais){

    }
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);

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

        System.out.print("Informe os símbolos da fita (separados por espaço, incluindo o símbolo branco '_'): ");
        String[] simbolosFitaArray = sc.nextLine().split("\\s+");
        List<String> simbolosFita = Arrays.asList(simbolosFitaArray);

        List<Transicao> funcaoTransicao = new ArrayList<>();

        System.out.println("Preencha as transições:");
        for (String estado : estados) {
            for (String simbolo : simbolosFita) {
                System.out.print(" δ (" + estado + "," + simbolo + "): ");
                String transicao = sc.nextLine().trim();

                if (!transicao.equalsIgnoreCase("X")) {
                    String[] partesTransicao = transicao.split(",");
                    String proximoEstado = partesTransicao[0];
                    String simboloParaEscrever = partesTransicao[1];
                    String direcao = partesTransicao[2];
                    funcaoTransicao.add(new Transicao(estado, simbolo, proximoEstado, simboloParaEscrever, direcao));
                }
            }
        }
}
