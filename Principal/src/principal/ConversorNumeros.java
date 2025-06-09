package principal; // Define que esta classe pertence ao pacote 'principal'

import javax.swing.JOptionPane; // Importa a classe JOptionPane para exibir caixas de diálogo simples

public class ConversorNumeros { // Declaração da classe pública ConversorNumeros

    // Método público que converte uma String para um inteiro (int)
    public int stringToInt(String numero) {
        try { // Inicia um bloco try-catch para tratar possíveis erros de conversão
            // Tenta converter a String 'numero' para um inteiro usando o método parseInt da classe Integer
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) { // Captura a exceção NumberFormatException se a String não for um número inteiro válido
            // Exibe uma mensagem de erro para o usuário usando JOptionPane
            JOptionPane.showMessageDialog(null, "Erro: '" + numero + "' não é um inteiro válido.", "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            // Retorna 0 como um valor padrão em caso de erro. Em um sistema mais robusto, poderia lançar uma exceção personalizada.
            return 0;
        }
    }

    // Método público que converte uma String para um número de ponto flutuante de precisão dupla (double)
    public double stringToDouble(String numero) {
        try { // Inicia um bloco try-catch para tratar possíveis erros de conversão
            // Tenta converter a String 'numero' para um double.
            // O método replace(",", ".") é usado para garantir que vírgulas (comuns em formatos numéricos brasileiros)
            // sejam tratadas como pontos decimais, que é o esperado por Double.parseDouble.
            return Double.parseDouble(numero.replace(",", "."));
        } catch (NumberFormatException e) { // Captura a exceção NumberFormatException se a String não for um número double válido
            // Exibe uma mensagem de erro para o usuário usando JOptionPane
            JOptionPane.showMessageDialog(null, "Erro: '" + numero + "' não é um número válido.", "Erro de Conversão", JOptionPane.ERROR_MESSAGE);
            // Retorna 0.0 como um valor padrão em caso de erro.
            return 0.0;
        }
    }
}