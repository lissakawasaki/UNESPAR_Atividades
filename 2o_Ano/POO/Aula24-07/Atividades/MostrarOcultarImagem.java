import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class MostrarOcultarImagem extends JFrame {
    private JButton btMostrar, btOcultar;
    private JLabel lbImagem;
    private JPanel painel;
    private Random random;

    public static void main(String[] args) {
        JFrame janela = new MostrarOcultarImagem();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public MostrarOcultarImagem() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setTitle("Mostrar e Ocultar Imagem");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        painel = new JPanel();
        painel.setBounds(0, 0, 500, 500);
        painel.setLayout(null);
        add(painel);

        btMostrar = new JButton("Mostrar");
        btMostrar.setBounds(50, 400, 100, 30);
        painel.add(btMostrar);

        btOcultar = new JButton("Ocultar");
        btOcultar.setBounds(200, 400, 100, 30);
        painel.add(btOcultar);

        lbImagem = new JLabel(new ImageIcon("cachorrococacola.jpg"));
        lbImagem.setSize(lbImagem.getPreferredSize());
        lbImagem.setVisible(false);
        painel.add(lbImagem);

        random = new Random();
    }

    public void definirEventos() {
        btMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int maxWidth = painel.getWidth() - lbImagem.getWidth();
                int maxHeight = painel.getHeight() - lbImagem.getHeight() - 50; 

                if (maxWidth > 0 && maxHeight > 0) {
                    int x = random.nextInt(maxWidth);
                    int y = random.nextInt(maxHeight);
                    lbImagem.setLocation(x, y);
                    lbImagem.setVisible(true);
                } else {
                    lbImagem.setVisible(false); 
                }
            }
        });

        btOcultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lbImagem.setVisible(false);
            }
        });
    }
}
