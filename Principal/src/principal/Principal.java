package principal; // Define que esta classe pertence ao pacote 'principal'

import javax.swing.SwingUtilities;// Importa SwingUtilities para garantir que a GUI seja criada na Event Dispatch Thread (EDT)
import loginrelacionados.LoginGUI;


public class Principal { // Declaração da classe Principal, que contém o método main
    // Método main - ponto de entrada da aplicação
    public static void main(String[] args) {
         new LoginGUI();
     
    }
}
    