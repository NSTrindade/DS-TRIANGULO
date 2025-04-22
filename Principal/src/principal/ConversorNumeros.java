package principal;

/** 
 * Classe para converter Strings (textos) em números inteiros ou decimais.
 */ 
public class ConversorNumeros { 

    
    public int stringToInt(String numeroString) { 
        if (numeroString == null || numeroString.trim().isEmpty()) {
             System.err.println("Erro: Entrada nula ou vazia para conversão de inteiro."); 
            return 0; // Retorna 0 para indicar erro/entrada inválida
        }
        try { 
            return Integer.parseInt(numeroString.trim()); 
        } catch (NumberFormatException e) { 
            System.err.println("Erro de conversão: '" + numeroString + "' não é um número inteiro válido."); 
            return 0; // Retorna 0 para indicar erro
        } 
    } 

    public double stringToDouble(String numeroString) { 
         if (numeroString == null || numeroString.trim().isEmpty()) {
             System.err.println("Erro: Entrada nula ou vazia para conversão de double."); 
            return 0.0; // Retorna 0.0 para indicar erro/entrada inválida
        }
        try { 
            String numeroFormatado = numeroString.replace(',', '.').trim(); 
            return Double.parseDouble(numeroFormatado); 
        } catch (NumberFormatException e) { 
            System.err.println("Erro de conversão: '" + numeroString + "' não é um número decimal válido."); 
            return 0.0; // Retorna 0.0 para indicar erro
        } 
    } 
}
