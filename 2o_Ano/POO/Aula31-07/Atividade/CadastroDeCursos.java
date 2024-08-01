import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class CadastroDeCursos {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Cursos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 340);
        frame.add(new CadastroCurso());
        frame.setVisible(true);
    }

    public static class CadastroCurso extends JPanel {

        private JTextField tfCodigo;
        private JTextField tfCurso;
        private JTextField tfCarga;
        private JComboBox<String> cbModalidade;
        private JComboBox<String> cbCaixas;
        private JButton btConsultar;
        private JButton btGravar;
        private JButton btLimpar;
        private Curso curso;

        public CadastroCurso() {
            inicializarComponentes();
            definirEventos();
        }

        private void inicializarComponentes() {
            setLayout(null);

            String[] cbCaixasItens = {"", "Extensão", "Graduação", "Pós Graduação"};
            String[] cbModalidadeItens = {"", "Presencial", "EAD", "Presencial/EAD"};
            JLabel lbCodigo = new JLabel("Código:");
            JLabel lbCurso = new JLabel("Nome Do Curso:");
            JLabel lbCarga = new JLabel("Carga Horária:");
            JLabel lbTipoCurso = new JLabel("Tipo de Curso:");
            JLabel lbModalidade = new JLabel("Modalidade:");

            tfCodigo = new JTextField();
            tfCurso = new JTextField();
            tfCarga = new JTextField();
            cbCaixas = new JComboBox<>(cbCaixasItens);
            cbModalidade = new JComboBox<>(cbModalidadeItens);

            btConsultar = new JButton("Consultar");
            btGravar = new JButton("Gravar");
            btLimpar = new JButton("Limpar");

            lbCodigo.setBounds(25, 20, 150, 25);
            tfCodigo.setBounds(130, 25, 50, 18);
            lbCurso.setBounds(25, 45, 150, 25);
            tfCurso.setBounds(130, 50, 215, 18);
            lbCarga.setBounds(25, 70, 150, 25);
            tfCarga.setBounds(130, 75, 215, 18);
            lbTipoCurso.setBounds(25, 100, 150, 25);
            cbCaixas.setBounds(130, 100, 150, 25);
            lbModalidade.setBounds(25, 130, 150, 25);
            cbModalidade.setBounds(130, 130, 150, 25);
            btConsultar.setBounds(25, 200, 100, 25);
            btGravar.setBounds(140, 200, 100, 25);
            btLimpar.setBounds(255, 200, 100, 25);

            add(lbCodigo);
            add(tfCodigo);
            add(lbCurso);
            add(tfCurso);
            add(lbCarga);
            add(tfCarga);
            add(lbTipoCurso);
            add(cbCaixas);
            add(lbModalidade);
            add(cbModalidade);
            add(btConsultar);
            add(btGravar);
            add(btLimpar);

            curso = new Curso();
        }

        private void definirEventos() {
            btGravar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (tfCodigo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Código não preenchido.");
                        tfCodigo.requestFocus();
                    } else if (tfCurso.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nome do curso não preenchido.");
                        tfCurso.requestFocus();
                    } else if (tfCarga.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Carga horária não preenchida.");
                        tfCarga.requestFocus();
                    } else if (cbCaixas.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Tipo de curso não selecionado.");
                        cbCaixas.requestFocus();
                    } else if (cbModalidade.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Modalidade não selecionada.");
                        cbModalidade.requestFocus();
                    } else {
                        curso.codigo = tfCodigo.getText();
                        curso.nomeCurso = tfCurso.getText();
                        curso.cargaHoraria = tfCarga.getText();
                        curso.tipoCurso = cbCaixas.getSelectedItem().toString();
                        curso.modalidade = cbModalidade.getSelectedItem().toString();
                        JOptionPane.showMessageDialog(null, curso.gravar("c:/temp"));
                    }
                }
            });

            btLimpar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tfCodigo.setText("");
                    tfCurso.setText("");
                    tfCarga.setText("");
                    cbCaixas.setSelectedIndex(0);
                    cbModalidade.setSelectedIndex(0);
                }
            });

            btConsultar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String codigoCurso = JOptionPane.showInputDialog("Digite o código do curso a ser consultado:");
                    if (codigoCurso != null && !codigoCurso.trim().isEmpty()) {
                        Curso cursoConsultado = Curso.ler("c:/temp", codigoCurso);
                        if (cursoConsultado != null) {
                            tfCodigo.setText(cursoConsultado.codigo);
                            tfCurso.setText(cursoConsultado.nomeCurso);
                            tfCarga.setText(cursoConsultado.cargaHoraria);
                            cbCaixas.setSelectedItem(cursoConsultado.tipoCurso);
                            cbModalidade.setSelectedItem(cursoConsultado.modalidade);
                        } else {
                            JOptionPane.showMessageDialog(null, "Curso não encontrado");
                        }
                    }
                }
            });
        }
    }

    public static class Curso {
        public String codigo, nomeCurso, cargaHoraria, tipoCurso, modalidade;

        public String gravar(String path) {
            try {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                PrintWriter pw = new PrintWriter(path + "/" + codigo + ".txt");
                pw.write(codigo + "\n");
                pw.write(nomeCurso + "\n");
                pw.write(cargaHoraria + "\n");
                pw.write(tipoCurso + "\n");
                pw.write(modalidade + "\n");
                pw.close();
                return "Curso gravado com sucesso!";
            } catch (IOException erro) {
                return "Erro ao gravar o curso: " + erro.toString();
            }
        }

        public static Curso ler(String path, String codigo) {
            try (BufferedReader br = new BufferedReader(new FileReader(path + "/" + codigo + ".txt"))) {
                Curso curso = new Curso();
                curso.codigo = br.readLine();
                curso.nomeCurso = br.readLine();
                curso.cargaHoraria = br.readLine();
                curso.tipoCurso = br.readLine();
                curso.modalidade = br.readLine();
                return curso;
            } catch (IOException e) {
                return null;
            }
        }
    }
}
