import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircuitoCombustivel extends JFrame {
    private JTextField resistenciaIndicadorField;
    private JTextField resistenciaBoiaField;
    private JTextField tensaoFonteField;
    private JLabel resultadoLabel;

    public CircuitoCombustivel() {
        // Configurações da janela principal
        setTitle("Cálculo de Corrente no Circuito de Combustível");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 240, 245)); // Cor de fundo pastel

        // Painel para os botões principais
        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Layout de grade
        botoesPanel.setOpaque(false);

        // Botões para cada condição do tanque
        JButton tanqueVazioButton = criarBotao("Tanque Vazio", new Color(255, 182, 193));
        tanqueVazioButton.addActionListener(e -> abrirJanelaTanque(0)); // Abre janela com tanque vazio

        JButton tanqueMeioButton = criarBotao("Tanque pela Metade", new Color(255, 192, 203));
        tanqueMeioButton.addActionListener(e -> abrirJanelaTanque(50)); // Abre janela com tanque pela metade

        JButton tanqueCheioButton = criarBotao("Tanque Cheio", new Color(255, 105, 180));
        tanqueCheioButton.addActionListener(e -> abrirJanelaTanque(100)); // Abre janela com tanque cheio

        botoesPanel.add(tanqueVazioButton);
        botoesPanel.add(tanqueMeioButton);
        botoesPanel.add(tanqueCheioButton);

        add(botoesPanel, BorderLayout.CENTER);
    }

    // Método para criar um botão com o texto e a cor de fundo especificados
    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botao.setForeground(Color.WHITE);
        botao.setBackground(corFundo);
        botao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(200, 40));
        return botao;
    }

    // Método para abrir uma nova janela com o nível de combustível especificado
    private void abrirJanelaTanque(int nivelCombustivel) {
        JFrame janelaTanque = new JFrame();
        janelaTanque.setTitle("Tanque com " + nivelCombustivel + "% de Combustível");
        janelaTanque.setSize(500, 400);
        janelaTanque.setLocationRelativeTo(null);
        janelaTanque.setLayout(new BorderLayout());
        janelaTanque.getContentPane().setBackground(new Color(255, 255, 255)); // Cor de fundo suave

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout()); // Layout flexível para melhor disposição dos elementos
        inputPanel.setBackground(new Color(255, 255, 255));

        Font labelFont = new Font("Tahoma", Font.PLAIN, 14);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.anchor = GridBagConstraints.WEST;

        // Adiciona campos e botões ao painel de entrada de dados
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(createLabel("Resistência do Indicador (Ω):", labelFont), gbc);

        gbc.gridx = 1;
        resistenciaIndicadorField = criarCampoTexto();
        inputPanel.add(resistenciaIndicadorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(createLabel("Resistência da Boia (Ω):", labelFont), gbc);

        gbc.gridx = 1;
        resistenciaBoiaField = criarCampoTexto();
        inputPanel.add(resistenciaBoiaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(createLabel("Tensão da Fonte (V):", labelFont), gbc);

        gbc.gridx = 1;
        tensaoFonteField = criarCampoTexto();
        inputPanel.add(tensaoFonteField, gbc);

        // Botão de calcular corrente
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton calcularButton = criarBotao("Calcular Corrente", new Color(255, 160, 122));
        calcularButton.addActionListener(e -> calcularCorrente());
        inputPanel.add(calcularButton, gbc);

        // Botão de limpar campos
        gbc.gridy = 4;
        JButton limparButton = criarBotao("Limpar", new Color(255, 192, 203));
        limparButton.addActionListener(e -> limparCampos());
        inputPanel.add(limparButton, gbc);

        // Botão de voltar
        gbc.gridy = 5;
        JButton voltarButton = criarBotao("Voltar", new Color(255, 105, 180));
        voltarButton.addActionListener(e -> janelaTanque.dispose());
        inputPanel.add(voltarButton, gbc);

        // Painel para exibir o resultado
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setBackground(new Color(255, 240, 245)); // Cor pastel suave
        resultadoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                "Resultado",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 16),
                new Color(255, 105, 180))
        );

        resultadoLabel = createLabel("Corrente: ", new Font("Tahoma", Font.BOLD, 18));
        resultadoLabel.setForeground(new Color(255, 105, 180)); // Cor pastel para maior destaque
        resultadoPanel.add(resultadoLabel);

        // Painel para desenhar o tanque
        JPanel desenhoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharTanque(g, nivelCombustivel);
            }
        };
        desenhoPanel.setPreferredSize(new Dimension(200, 300));

        janelaTanque.add(inputPanel, BorderLayout.CENTER); // Adiciona o painel de entrada
        janelaTanque.add(resultadoPanel, BorderLayout.SOUTH); // Adiciona o painel de resultado
        janelaTanque.add(desenhoPanel, BorderLayout.EAST); // Adiciona o painel de desenho

        janelaTanque.setVisible(true); // Torna a janela visível
    }

    // Método para criar um rótulo com o texto e a fonte especificados
    private JLabel createLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        label.setForeground(new Color(60, 60, 60)); // Cor do texto
        return label;
    }

    // Método para criar um campo de texto
    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(150, 30));
        return campo;
    }

    // Método para desenhar o tanque e o nível de combustível
    private void desenharTanque(Graphics g, int nivelCombustivel) {
        int alturaTanque = 200;
        int larguraTanque = 100;
        int x = 50;
        int y = 50;

        // Desenha o contorno do tanque
        g.setColor(new Color(200, 200, 200));
        g.drawRect(x, y, larguraTanque, alturaTanque);

        // Desenha o nível de combustível
        int alturaCombustivel = (int) (alturaTanque * (nivelCombustivel / 100.0));
        g.setColor(new Color(100, 150, 200));
        g.fillRect(x, y + (alturaTanque - alturaCombustivel), larguraTanque, alturaCombustivel);
    }

    // Método para calcular a corrente com base nos valores inseridos
    private void calcularCorrente() {
        try {
            // Verifica se todos os campos foram preenchidos
            if (resistenciaIndicadorField.getText().isEmpty() || resistenciaBoiaField.getText().isEmpty() || tensaoFonteField.getText().isEmpty()) {
                throw new NumberFormatException("Campos vazios");
            }

            // Obtém os valores dos campos de texto
            double resistenciaIndicador = Double.parseDouble(resistenciaIndicadorField.getText());
            double resistenciaBoia = Double.parseDouble(resistenciaBoiaField.getText());
            double tensaoFonte = Double.parseDouble(tensaoFonteField.getText());

            // Calcula a corrente
            double resistenciaTotal = resistenciaIndicador + resistenciaBoia;
            double corrente = tensaoFonte / resistenciaTotal;

            resultadoLabel.setText(String.format("Corrente: %.3f A", corrente)); // Atualiza o rótulo com o resultado
        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se houver problemas com os valores inseridos
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos em todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos de entrada e o resultado
    private void limparCampos() {
        resistenciaIndicadorField.setText("");
        resistenciaBoiaField.setText("");
        tensaoFonteField.setText("");
        resultadoLabel.setText("Corrente: ");
    }

    public static void main(String[] args) {
        // Inicializa a aplicação
        SwingUtilities.invokeLater(() -> new CircuitoCombustivel().setVisible(true));
    }
}
