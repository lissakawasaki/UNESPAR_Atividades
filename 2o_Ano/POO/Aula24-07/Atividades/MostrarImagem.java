import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MostrarImagem extends JFrame {
    JButton btMostrarImagem;
    JLabel lbImagem;
    int posicaoEsquerda = 100, posicaoTopo = 100;

    public static void main(String[] args) {
        JFrame janela = new MostrarImagem();

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public MostrarImagem() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Mostrar imagem");
        setSize(300, 300);
        setLocation(posicaoEsquerda, posicaoTopo);
        setLayout(new FlowLayout());

        btMostrarImagem = new JButton("Passe o Mouse");
        add(btMostrarImagem);

        lbImagem = new JLabel(new ImageIcon("miau.jpg"));
        lbImagem.setVisible(false); // Inicialmente, a imagem não é visível
        add(lbImagem);
    }

    public void definirEventos() {
        btMostrarImagem.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lbImagem.setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                lbImagem.setVisible(false);
            }
        });
    }
}

