package principal;

import javax.swing.JOptionPane; 

/** 
 * Classe para lidar com a entrada e saída de dados usando interfaces gráficas simples (JOptionPane).
 */ 
public class EntradaSaidaDados { 

    // Construtor vazio.
    public EntradaSaidaDados() { 
    } 

    public String entradaDados(String mensagemEntrada) { 
        String input = JOptionPane.showInputDialog(null, mensagemEntrada, "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
        return input; 
    } 

    /** 
     * Mostra uma caixa de diálogo simples exibindo uma mensagem para o usuário.
     * @param mensagemSaida A mensagem a ser exibida na caixa de diálogo. 
     */ 
    public void saidaDados(String mensagemSaida) { 
        JOptionPane.showMessageDialog(null, mensagemSaida, "Informação", JOptionPane.INFORMATION_MESSAGE);
    } 

    public void saidaDadosErro(String mensagemErro) { 
        JOptionPane.showMessageDialog(null, mensagemErro, "Erro", JOptionPane.ERROR_MESSAGE);
    } 
}
