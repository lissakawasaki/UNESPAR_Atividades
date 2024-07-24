import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class MensagemAtendimento extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MensagemAtendimento frame = new MensagemAtendimento();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public MensagemAtendimento() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Mensagem");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelMensagem = new JPanel();
        JLabel labelMensagem = new JLabel(obterMensagem());
        painelMensagem.add(labelMensagem);
        add(painelMensagem, BorderLayout.CENTER);

        JPanel painelBotao = new JPanel();
        JButton botaoOk = new JButton("OK");
        painelBotao.add(botaoOk);
        add(painelBotao, BorderLayout.SOUTH);

        botaoOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private String obterMensagem() {
        LocalDateTime agora = LocalDateTime.now();
        DayOfWeek diaDaSemana = agora.getDayOfWeek();
        int hora = agora.getHour();

        if (diaDaSemana != DayOfWeek.SATURDAY && diaDaSemana != DayOfWeek.SUNDAY && hora >= 8 && hora < 17) {
            return diaDaSemana + " - " + hora + "h - Estamos Atendendo";
        } else {
            return "Expediente encerrado";
        }
    }
}
