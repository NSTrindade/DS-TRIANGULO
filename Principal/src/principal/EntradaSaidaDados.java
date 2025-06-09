package principal; // Define que esta classe pertence ao pacote 'principal'

import javax.swing.JOptionPane; // Importa a classe JOptionPane para interações de entrada e saída com o usuário

public class EntradaSaidaDados { // Declaração da classe pública EntradaSaidaDados

    // Método público para receber dados (entrada) do usuário
    // Recebe uma String 'mensagemEntrada' que será exibida na caixa de diálogo
    public String entradaDados(String mensagemEntrada) {
        // Exibe uma caixa de diálogo de entrada (showInputDialog) com a mensagem fornecida
        // e retorna a String digitada pelo usuário.
        return JOptionPane.showInputDialog(null, mensagemEntrada);
    }

    // Método público para exibir dados (saída) para o usuário
    // Recebe uma String 'mensagemSaida' que será exibida na caixa de diálogo
    public void saidaDados(String mensagemSaida) {
        // Exibe uma caixa de diálogo de mensagem (showMessageDialog) com a mensagem fornecida.
        // O primeiro argumento 'null' faz com que a caixa de diálogo apareça centralizada na tela.
        JOptionPane.showMessageDialog(null, mensagemSaida);
    }
}