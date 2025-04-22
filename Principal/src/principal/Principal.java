
package principal;

/** // 
 * Classe principal que serve apenas como ponto de entrada para a aplicação 
 * de cálculo de triângulos com interface gráfica.
 * Sua única responsabilidade é criar e iniciar o menu principal.
 */ // 
public class Principal {

    
    public static void main(String[] args) { 
        
        // Cria uma instância (um objeto) da classe MenuTriangulo.
        // Isso chama o construtor de MenuTriangulo, que inicializa todos os seus componentes internos (IO, conversor, etc.).
        MenuTriangulo menu = new MenuTriangulo(); 
        
        // Chama o método 'executarMenu' no objeto 'menu' que acabamos de criar.
        // Isso inicia o loop do menu e toda a interação com o usuário.
        menu.executarMenu(); 
        
        // Após o método executarMenu() terminar (quando o usuário escolhe sair), o programa finaliza.
        
    } 
} 
