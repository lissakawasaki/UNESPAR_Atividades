public class Fita {
    // Variável para armazenar a fita e a posição da cabeça de leitura/escrita
    private char[] fita;
    private int posicaoCabeca;

    // Construtor para inicializar a fita com a palavra de entrada e espaços em branco adicionais
    public Fita(String entrada) {
        this.fita = (entrada + "__________________").toCharArray(); // Adiciona espaços em branco para evitar sair dos limites
        this.posicaoCabeca = 0; // Inicializa a posição da cabeça no início da fita
    }

    // Método para ler o símbolo na posição atual da cabeça
    public char lerSimbolo() {
        return fita[posicaoCabeca];
    }

    // Método para escrever um símbolo na posição atual da cabeça
    public void escreverSimbolo(char simbolo) {
        fita[posicaoCabeca] = simbolo;
    }

    // Método para mover a cabeça de leitura/escrita para a direita ou esquerda
    public void moverCabeca(char direcao) {
        if (direcao == 'R') {
            posicaoCabeca++;
        } else if (direcao == 'L') {
            posicaoCabeca--;
        }
    }

    // Método para verificar se a posição da cabeça está dentro dos limites da fita
    public boolean posicaoValida() {
        return posicaoCabeca >= 0 && posicaoCabeca < fita.length;
    }
}
