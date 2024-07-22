public class Fita {
    // Variável de instância para armazenar a fita e a posição da cabeça de leitura/escrita
    private char[] fita;
    private int posicaoCabeca;
    private char marcadorInicio;

    // Construtor para inicializar a fita com a palavra de entrada e espaços em branco adicionais
    public Fita(String entrada, char marcadorInicio) {
        this.marcadorInicio = marcadorInicio;
        this.fita = (marcadorInicio + entrada).toCharArray(); 
        this.posicaoCabeca =  1; // Inicializa a posição da cabeça logo após o marcador de início
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
            if (posicaoCabeca >= fita.length) {
                expandirFita();
            }
        } else if (direcao == 'L') {
            if (posicaoCabeca > 0) {
                posicaoCabeca--;
            } else {
                System.out.println("Movimento para a esquerda fora dos limites da fita."); // ArrayIndexOutOfBoundsException
            }
        }
    }

    // Método para verificar se a posição da cabeça está dentro dos limites da fita
    public boolean posicaoValida() {
        return posicaoCabeca >= 0 && posicaoCabeca < fita.length;
    }

    // Método para expandir a fita se necessário
    private void expandirFita() {
        char[] novaFita = new char[fita.length + 10]; // Expande a fita em 10 posições
        System.arraycopy(fita, 0, novaFita, 0, fita.length);
        for (int i = fita.length; i < novaFita.length; i++) {
            novaFita[i] = '_'; // Preenche com símbolos em branco
        }
        novaFita[novaFita.length - 1] = marcadorInicio; // Adiciona o marcador de início no final
        fita = novaFita;
    }

    // Método para retornar a fita em formato de string, destacando a posição da cabeça
    public String toString() {
        return new String(fita, 0, posicaoCabeca) + "[" + fita[posicaoCabeca] + "]" + new String(fita, posicaoCabeca + 1, fita.length - posicaoCabeca - 1);
    }
}
