package Departamentos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCadastroCurso extends JPanel {
    private JTextField txtCodigoCurso, txtNomeCurso, txtCargaHoraria, txtTipoCurso, txtModalidadeCurso, txtCodigoDepartamento;
    private JButton btnCadastrar, btnCancelar;
    
    public GuiCadastroCurso() {
        setLayout(new GridLayout(7, 2, 10, 10));
        
        add(new JLabel("Código do Curso:"));
        txtCodigoCurso = new JTextField();
        add(txtCodigoCurso);
        
        add(new JLabel("Nome do Curso:"));
        txtNomeCurso = new JTextField();
        add(txtNomeCurso);
        
        add(new JLabel("Carga Horária:"));
        txtCargaHoraria = new JTextField();
        add(txtCargaHoraria);
        
        add(new JLabel("Modalidade:"));
        txtModalidadeCurso = new JTextField();
        add(txtModalidadeCurso);
        
        add(new JLabel("Tipo do Curso:"));
        txtTipoCurso = new JTextField();
        add(txtTipoCurso);
        
        add(new JLabel("Código do Departamento:"));
        txtCodigoDepartamento = new JTextField();
        add(txtCodigoDepartamento);
        
        btnCadastrar = new JButton("Cadastrar");
        btnCancelar = new JButton("Cancelar");
        
        add(btnCadastrar);
        add(btnCancelar);
        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCurso();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void cadastrarCurso() {
        try {
            Cursos curso = new Cursos();
            curso.setCodigoCurso(Integer.parseInt(txtCodigoCurso.getText()));
            curso.setNomeCurso(txtNomeCurso.getText());
            curso.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText()));
            curso.setModalidadeCurso(txtModalidadeCurso.getText());
            curso.setTipoCurso(txtTipoCurso.getText());
            curso.setIdCurso(Integer.parseInt(txtCodigoDepartamento.getText()));  // Assumindo que o Código do Departamento é o ID do curso
            
            JOptionPane.showMessageDialog(this, "Curso cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos nos campos numéricos.");
        }
    }

    private void limparCampos() {
        txtCodigoCurso.setText("");
        txtNomeCurso.setText("");
        txtCargaHoraria.setText("");
        txtModalidadeCurso.setText("");
        txtTipoCurso.setText("");
        txtCodigoDepartamento.setText("");
    }
}
