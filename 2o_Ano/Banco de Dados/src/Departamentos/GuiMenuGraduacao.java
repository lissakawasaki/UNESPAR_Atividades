package Departamentos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Classe para gerenciar o menu de Graduação
public class GuiMenuGraduacao extends JPanel {
    private Container contentPane;
    private JMenuBar menuBarPrincipal;
    private ArrayList<String> cursos;
    private ArrayList<String> disciplinas;

    public GuiMenuGraduacao(Container contentPane, JMenuBar menuBarPrincipal) {
        this.contentPane = contentPane;
        this.menuBarPrincipal = menuBarPrincipal;
        this.cursos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Criação do painel de opções
        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new FlowLayout());

        JButton btnCadastroCursos = new JButton("Cadastro de Cursos");
        JButton btnCadastroDisciplinas = new JButton("Cadastro de Disciplinas");
        JButton btnVoltar = new JButton("Voltar");

        painelOpcoes.add(btnCadastroCursos);
        painelOpcoes.add(btnCadastroDisciplinas);
        painelOpcoes.add(btnVoltar);

        add(painelOpcoes, BorderLayout.NORTH);

        // Painel central para exibir formulários
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new CardLayout());

        // Formulário de Cadastro de Cursos
        JPanel painelCadastroCursos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        JLabel lblNomeCurso = new JLabel("Nome do Curso:");
        JTextField tfNomeCurso = new JTextField(20);
        JButton btnSalvarCurso = new JButton("Salvar Curso");

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastroCursos.add(lblNomeCurso, gbc);

        gbc.gridx = 1;
        painelCadastroCursos.add(tfNomeCurso, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        painelCadastroCursos.add(btnSalvarCurso, gbc);

        // Formulário de Cadastro de Disciplinas
        JPanel painelCadastroDisciplinas = new JPanel(new GridBagLayout());

        JLabel lblNomeDisciplina = new JLabel("Nome da Disciplina:");
        JTextField tfNomeDisciplina = new JTextField(20);
        JButton btnSalvarDisciplina = new JButton("Salvar Disciplina");

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastroDisciplinas.add(lblNomeDisciplina, gbc);

        gbc.gridx = 1;
        painelCadastroDisciplinas.add(tfNomeDisciplina, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        painelCadastroDisciplinas.add(btnSalvarDisciplina, gbc);

        // Adiciona os formulários ao painel central
        painelCentral.add(painelCadastroCursos, "CadastroCursos");
        painelCentral.add(painelCadastroDisciplinas, "CadastroDisciplinas");

        add(painelCentral, BorderLayout.CENTER);
    }

    private void definirEventos() {
        // Ação para o botão de Cadastro de Cursos
        JButton btnCadastroCursos = (JButton)((JPanel)getComponent(0)).getComponent(0);
        btnCadastroCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(((Container) getComponent(1)).getLayout());
                cl.show((Container) getComponent(1), "CadastroCursos");
            }
        });

        // Ação para o botão de Cadastro de Disciplinas
        JButton btnCadastroDisciplinas = (JButton)((JPanel)getComponent(0)).getComponent(1);
        btnCadastroDisciplinas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(((Container) getComponent(1)).getLayout());
                cl.show((Container) getComponent(1), "CadastroDisciplinas");
            }
        });

        // Ação para o botão de Voltar
        JButton btnVoltar = (JButton)((JPanel)getComponent(0)).getComponent(2);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Restaura o menu principal e o painel inicial
                contentPane.removeAll();
                // Implementa o retorno ao painel inicial de GuiMenuSistema
                GuiMenuSistema janelaPrincipal = new GuiMenuSistema();
                contentPane.add(((Object) janelaPrincipal).getPainelInicial(), BorderLayout.CENTER);
                janelaPrincipal.setJMenuBar(menuBarPrincipal); // Definido no JFrame
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        // Ação para salvar curso
        JPanel painelCentral = (JPanel)getComponent(1);
        JPanel painelCadastroCursos = (JPanel)painelCentral.getComponent(0);
        JButton btnSalvarCurso = (JButton)((JPanel)painelCadastroCursos).getComponent(2);
        JTextField tfNomeCurso = (JTextField)((JPanel)painelCadastroCursos).getComponent(1);

        btnSalvarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeCurso = tfNomeCurso.getText().trim();
                if(!nomeCurso.isEmpty()) {
                    cursos.add(nomeCurso);
                    JOptionPane.showMessageDialog(null, "Curso '" + nomeCurso + "' cadastrado com sucesso!");
                    tfNomeCurso.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o nome do curso.");
                }
            }
        });

        // Ação para salvar disciplina
        JPanel painelCadastroDisciplinas = (JPanel)painelCentral.getComponent(1);
        JButton btnSalvarDisciplina = (JButton)((JPanel)painelCadastroDisciplinas).getComponent(2);
        JTextField tfNomeDisciplina = (JTextField)((JPanel)painelCadastroDisciplinas).getComponent(1);

        btnSalvarDisciplina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeDisciplina = tfNomeDisciplina.getText().trim();
                if(!nomeDisciplina.isEmpty()) {
                    disciplinas.add(nomeDisciplina);
                    JOptionPane.showMessageDialog(null, "Disciplina '" + nomeDisciplina + "' cadastrada com sucesso!");
                    tfNomeDisciplina.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o nome da disciplina.");
                }
            }
        });
    }

    // Método para obter a barra de menu atual (pode ser usada para restaurar)
    public JMenuBar getMenuBar() {
        return menuBarPrincipal;
    }
}
