import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MudarCor extends JFrame {
    JButton btMudarCor;
    int posicaoEsquerda = 100, posicaoTopo = 100;

    public static void main(String[] args) {
        JFrame janela = new MudarCor();

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public MudarCor() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Mudar cor do botão");
        setSize(250, 150);
        setLocation(posicaoEsquerda, posicaoTopo);
        setLayout(new FlowLayout());

        btMudarCor = new JButton("Trocar");
        btMudarCor.setBackground(Color.yellow);
        add(btMudarCor);
    }

    public void definirEventos() {
        btMudarCor.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btMudarCor.setBackground(Color.orange);
            }

            public void mouseExited(MouseEvent e) {
                btMudarCor.setBackground(Color.yellow);
            }
        });
    }
}
