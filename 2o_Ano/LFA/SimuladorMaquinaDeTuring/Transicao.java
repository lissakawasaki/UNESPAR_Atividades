public class Transicao {
    // Variáveis para armazenar o próximo estado, símbolo a ser escrito e a direção do movimento
    private String proximoEstado;
    private char simboloFita;
    private char direcao;

    // Construtor para inicializar uma transição com estado, símbolo e direção fornecidos
    public Transicao(String proximoEstado, char simboloFita, char direcao) {
        this.proximoEstado = proximoEstado;
        this.simboloFita = simboloFita;
        this.direcao = direcao;
    }

    // Métodos getters para acessar os valores das variáveis de instância
    public String getProximoEstado() {
        return proximoEstado;
    }

    public char getsimboloFita() {
        return simboloFita;
    }

    public char getDirecao() {
        return direcao;
    }
}
