package Departamentos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiMenuSistema extends JFrame {
    private Container contentPane;
    private JPanel painelInicial;
    private JMenuBar mnBarra;
    private JMenu mnArquivo, mnSistemas, mnGraduacao, mnCadastros, mnConsultas;
    private JMenuItem miSair, miCadastrarCurso, miCadastrarDisciplina;

    public static void main(String[] args) {
        JFrame janela = new GuiMenuSistema();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
    }

    public GuiMenuSistema() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Menu Principal");
        setBounds(0, 0, 800, 600);
        contentPane = getContentPane();

        painelInicial = new JPanel(new BorderLayout());
        JLabel msgMenuPrincipal = new JLabel("Menu Principal", JLabel.CENTER);
        painelInicial.add(msgMenuPrincipal, BorderLayout.CENTER);
        contentPane.add(painelInicial, BorderLayout.CENTER);

        mnBarra = new JMenuBar();
        mnArquivo = new JMenu("Arquivo");
        mnArquivo.setMnemonic('A');
        mnSistemas = new JMenu("Sistemas");
        mnSistemas.setMnemonic('S');
        mnGraduacao = new JMenu("Graduação");
        mnGraduacao.setMnemonic('G');

        miSair = new JMenuItem("Sair");
        miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

        mnArquivo.add(miSair);

        // Adicionando submenus Cadastros e Consultas dentro de Graduação
        mnCadastros = new JMenu("Cadastros");
        mnConsultas = new JMenu("Consultas");

        miCadastrarCurso = new JMenuItem("Cadastro de Cursos");
        miCadastrarDisciplina = new JMenuItem("Cadastro de Disciplinas");

        mnCadastros.add(miCadastrarCurso);
        mnCadastros.add(miCadastrarDisciplina);

        mnGraduacao.add(mnCadastros);
        mnGraduacao.add(mnConsultas); // Consultas sem ações por enquanto

        mnSistemas.add(mnGraduacao);
        mnSistemas.add(new JMenuItem("Biblioteca"));
        mnSistemas.add(new JMenuItem("Restaurante"));
        mnSistemas.add(new JMenuItem("Finanças"));

        mnBarra.add(mnArquivo);
        mnBarra.add(mnSistemas);

        setJMenuBar(mnBarra);
    }

    private void definirEventos() {
        miSair.addActionListener(e -> System.exit(0));

        miCadastrarCurso.addActionListener(e -> {
            contentPane.removeAll();
            contentPane.add(new GuiCadastroCurso());
            contentPane.revalidate();
            contentPane.repaint();
        });

        miCadastrarDisciplina.addActionListener(e -> {
            contentPane.removeAll();
            contentPane.add(new GuiCadastroDisciplina());
            contentPane.revalidate();
            contentPane.repaint();
        });
    }
}

// Tela de Cadastro de Curso
class GuiCadastroCurso extends JPanel {
    public GuiCadastroCurso() {
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Código do Curso:"));
        add(new JTextField());

        add(new JLabel("Nome do Curso:"));
        add(new JTextField());

        add(new JLabel("Carga Horária:"));
        add(new JTextField());

        add(new JLabel("Modalidade (EAD/Presencial):"));
        JComboBox<String> modalidade = new JComboBox<>(new String[]{"EAD", "Presencial"});
        add(modalidade);

        add(new JLabel("Tipo de Curso:"));
        JComboBox<String> tipoCurso = new JComboBox<>(new String[]{"Graduação", "Licenciatura", "Bacharelado"});
        add(tipoCurso);

        add(new JLabel("Código do Departamento:"));
        add(new JTextField());

        // Adicionando os botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(new JButton("Gravar"));
        panelBotoes.add(new JButton("Alterar"));
        panelBotoes.add(new JButton("Excluir"));
        panelBotoes.add(new JButton("Localizar"));
        panelBotoes.add(new JButton("Novo"));
        panelBotoes.add(new JButton("Cancelar"));
        panelBotoes.add(new JButton("Fechar"));

        add(panelBotoes);
    }
}

// Tela de Cadastro de Disciplinas
class GuiCadastroDisciplina extends JPanel {
    public GuiCadastroDisciplina() {
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Código:"));
        add(new JTextField());

        add(new JLabel("Curso:"));
        JComboBox<String> cursos = new JComboBox<>(new String[]{"Curso 1", "Curso 2"}); // Exemplo de cursos
        add(cursos);

        add(new JLabel("Nome:"));
        add(new JTextField());

        add(new JLabel("Carga Horária:"));
        add(new JTextField());

        add(new JLabel("Área/Matéria:"));
        add(new JTextField());

        // Adicionando os botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(new JButton("Gravar"));
        panelBotoes.add(new JButton("Alterar"));
        panelBotoes.add(new JButton("Excluir"));
        panelBotoes.add(new JButton("Localizar"));
        panelBotoes.add(new JButton("Novo"));
        panelBotoes.add(new JButton("Cancelar"));
        panelBotoes.add(new JButton("Fechar"));

        add(panelBotoes);
    }
}
