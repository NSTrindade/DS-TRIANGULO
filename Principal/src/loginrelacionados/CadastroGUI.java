package loginrelacionados;

import javax.swing.*;
import loginrelacionados.LoginDAO;
import java.awt.*;
import java.awt.event.*;

public class CadastroGUI extends JFrame {

    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    public CadastroGUI() {
        setTitle("Cadastro - Calculadora de Triângulos");
        setSize(350, 230);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // desabilita redimensionamento

        JPanel painel = new JPanel();
        painel.setBackground(new Color(200, 220, 240));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("CADASTRO");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(0, 51, 102));

        txtNome = new JTextField(15);
        txtSenha = new JPasswordField(15);

        btnCadastrar = criarBotao("CADASTRAR");

        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarLinha("Usuário:", txtNome));
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(criarLinha("Senha:", txtSenha));
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(btnCadastrar);

        add(painel);
        setVisible(true);

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            try {
                int senha = Integer.parseInt(new String(txtSenha.getPassword()));
                LoginDAO dao = new LoginDAO();

                if (dao.cadastrar(nome, senha)) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Senha deve ser numérica.");
            }
        });
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setForeground(new Color(0, 51, 102));
        botao.setBackground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        botao.setMaximumSize(new Dimension(200, 40));
        return botao;
    }

    private JPanel criarLinha(String rotulo, JComponent campo) {
        JPanel linha = new JPanel(new FlowLayout());
        linha.setBackground(new Color(200, 220, 240));
        JLabel label = new JLabel(rotulo);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(0, 51, 102));
        linha.add(label);
        linha.add(campo);
        return linha;
    }
}
