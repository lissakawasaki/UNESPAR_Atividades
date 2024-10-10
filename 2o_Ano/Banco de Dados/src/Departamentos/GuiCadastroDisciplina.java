package Departamentos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCadastroDisciplina extends JPanel {
    private JTextField txtIdDisciplina, txtIdCurso, txtNomeDisciplina, txtCargaHoraria, txtAreaMateria;
    private JButton btnCadastrar, btnCancelar;
    
    public GuiCadastroDisciplina() {
        setLayout(new GridLayout(6, 2, 10, 10));
        
        add(new JLabel("ID da Disciplina:"));
        txtIdDisciplina = new JTextField();
        add(txtIdDisciplina);
        
        add(new JLabel("ID do Curso:"));
        txtIdCurso = new JTextField();
        add(txtIdCurso);
        
        add(new JLabel("Nome da Disciplina:"));
        txtNomeDisciplina = new JTextField();
        add(txtNomeDisciplina);
        
        add(new JLabel("Carga Horária:"));
        txtCargaHoraria = new JTextField();
        add(txtCargaHoraria);
        
        add(new JLabel("Área da Matéria:"));
        txtAreaMateria = new JTextField();
        add(txtAreaMateria);
        
        btnCadastrar = new JButton("Cadastrar");
        btnCancelar = new JButton("Cancelar");
        
        add(btnCadastrar);
        add(btnCancelar);
        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarDisciplina();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void cadastrarDisciplina() {
        try {
            Disciplina disciplina = new Disciplina();
            disciplina.setID_disciplina(txtIdDisciplina.getText());
            disciplina.setID_curso(txtIdCurso.getText());
            disciplina.setNome(txtNomeDisciplina.getText());
            disciplina.setCarga_horaria(Integer.parseInt(txtCargaHoraria.getText()));
            disciplina.setArea_materia(txtAreaMateria.getText());
            
            JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos nos campos numéricos.");
        }
    }

    private void limparCampos() {
        txtIdDisciplina.setText("");
        txtIdCurso.setText("");
        txtNomeDisciplina.setText("");
        txtCargaHoraria.setText("");
        txtAreaMateria.setText("");
    }
}
