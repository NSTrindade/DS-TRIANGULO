package principal; // Define que esta classe pertence ao pacote 'principal'

import javax.swing.*; // Importa todas as classes do pacote Swing para componentes GUI
import java.awt.*;    // Importa classes do AWT para gráficos, cores, fontes, etc.

// Declaração da classe CustomButton, que herda de JButton para criar um botão com estilo personalizado
public class CustomButton extends JButton {
    // Atributos para armazenar as cores de fundo para os estados hover (mouse sobre) e pressed (pressionado)
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    // Construtor da classe CustomButton, recebe o texto do botão como parâmetro
    public CustomButton(String text) {
        super(text); // Chama o construtor da classe pai (JButton) com o texto fornecido
        super.setContentAreaFilled(false); // Define que o JButton não deve pintar sua área de conteúdo padrão (para permitir pintura customizada)
        
        // Define a cor do texto (foreground) do botão
        setForeground(new Color(0, 110, 180)); // Um tom de azul escuro
        // Define a cor de fundo padrão do botão (quando não está em hover ou pressionado)
        setBackground(Color.WHITE);
        // Define que o "foco pintado" (geralmente uma borda pontilhada ao redor do texto quando o botão tem foco) não deve ser exibido
        setFocusPainted(false);
        // Define a fonte do texto do botão
        setFont(new Font("Arial", Font.BOLD, 16)); // Fonte Arial, negrito, tamanho 16
        // Define a borda do botão
        setBorder(BorderFactory.createCompoundBorder( // Cria uma borda composta (duas bordas em uma)
                BorderFactory.createLineBorder(new Color(0, 110, 180), 1), // Borda externa: linha azul de 1 pixel
                BorderFactory.createEmptyBorder(10, 25, 10, 25) // Borda interna: vazia, para criar preenchimento (padding) ao redor do texto
        ));
        // Define as cores para os estados hover e pressed
        hoverBackgroundColor = new Color(230, 245, 255); // Azul bem claro para hover
        pressedBackgroundColor = new Color(200, 230, 250); // Azul um pouco mais escuro para pressionado
    }

    // Sobrescreve o método paintComponent da classe JComponent (pai de JButton)
    // Este método é responsável por desenhar o componente.
    @Override
    protected void paintComponent(Graphics g) {
        // Obtém o modelo do botão para verificar seu estado (pressionado, rollover/hover)
        if (getModel().isPressed()) { // Se o botão está sendo pressionado
            g.setColor(pressedBackgroundColor); // Define a cor de desenho para a cor de fundo pressionado
        } else if (getModel().isRollover()) { // Se o mouse está sobre o botão (hover)
            g.setColor(hoverBackgroundColor); // Define a cor de desenho para a cor de fundo hover
        } else { // Se o botão está no estado normal (nem pressionado, nem hover)
            g.setColor(getBackground()); // Define a cor de desenho para a cor de fundo padrão
        }
        // Preenche o retângulo do botão com a cor definida acima, cobrindo toda a área do botão
        g.fillRect(0, 0, getWidth(), getHeight());
        // Chama o método paintComponent da classe pai (JButton) para desenhar o texto e outros elementos padrão do botão
        // Isso é importante para que o texto do botão ainda seja exibido sobre o fundo personalizado.
        super.paintComponent(g);
    }
}