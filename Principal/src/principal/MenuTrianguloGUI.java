package principal; // Define que esta classe pertence ao pacote 'principal'

// Importações necessárias para componentes Swing, layout, eventos e manipulação de listas
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList; // Importa ArrayList para criar a lista de triângulos em memória
import java.util.List;      // Importa a interface List

// Declaração da classe MenuTrianguloGUI, que herda de JFrame para ser a janela principal
public class MenuTrianguloGUI extends JFrame {
    // Atributo removido: private GerenciadorTriangulos gerenciadorTriangulos;
    // Nova lista para armazenar os triângulos apenas durante a sessão atual da aplicação.
    // Esta lista pertence à instância de MenuTrianguloGUI.
    private List<Triangulo> triangulosDaSessao;
    private Triangulo trianguloAtual; // Armazena o triângulo atualmente selecionado para operações

    // Instâncias de classes utilitárias e de serviço (marcadas como final pois não serão reatribuídas)
    private final ConversorNumeros conversor = new ConversorNumeros();
    private final Verifica verificador = new Verifica();
    
    // Definições de cores e fontes para estilização da GUI (marcadas como final)
    private final Color bgColor = new Color(210, 230, 245);
    private final Color panelColor = new Color(170, 205, 230);
    private final Color textColor = new Color(255, 255, 255);
    private final Color buttonTextColor = new Color(0, 110, 180);
    private final Font titleFont = new Font("Arial", Font.BOLD, 52);
    private final Font labelFont = new Font("Arial", Font.PLAIN, 14);

    private JLabel feedbackLabel; // Rótulo para exibir mensagens de feedback ao usuário

    // Construtor da classe MenuTrianguloGUI (modificado para não receber um Gerenciador)
    public MenuTrianguloGUI() {
        // Inicializa a lista 'triangulosDaSessao' como um novo ArrayList vazio.
        // Esta lista manterá os triângulos cadastrados apenas enquanto a aplicação estiver em execução.
        this.triangulosDaSessao = new ArrayList<>();

        // Configurações básicas da janela (JFrame)
        setTitle("Calculadora de Triângulos"); // Título da janela simplificado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a ação padrão ao fechar a janela
        setSize(500, 750); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout()); // Define o gerenciador de layout principal
        getContentPane().setBackground(bgColor); // Define a cor de fundo do painel de conteúdo
        
        initComponents(); // Chama o método para criar e adicionar os componentes da GUI
        
        // Como não há carregamento de triângulos de um arquivo, a lista começa vazia.
        // Atualiza o rótulo de feedback para refletir o estado inicial.
        updateFeedbackLabel("Nenhum triângulo cadastrado ou selecionado nesta sessão.");
    }

    // Método privado para criar e organizar os componentes da interface gráfica
    // Este método permanece estruturalmente o MESMO da versão anterior, pois a aparência do menu não mudou.
    // Os comentários aqui seriam idênticos aos da versão anterior para este método.
    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(bgColor);
        topPanel.setBorder(new EmptyBorder(20, 0, 10, 0)); 
        JLabel menuTitle = new JLabel("MENU");
        menuTitle.setFont(titleFont);
        menuTitle.setForeground(textColor);
        JPanel menuTitleWrapper = new JPanel(new BorderLayout());
        menuTitleWrapper.setBackground(buttonTextColor);
        menuTitleWrapper.setBorder(BorderFactory.createEmptyBorder(5,30,5,80));
        menuTitleWrapper.add(menuTitle, BorderLayout.CENTER);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(bgColor);
        headerPanel.add(menuTitleWrapper, BorderLayout.CENTER);
        topPanel.add(headerPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(bgColor);
        buttonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        String[] buttonLabels = {
            "1. CADASTRAR LADOS",
            "2. VERIFICAR LADOS CADASTRADOS",
            "3. VERIFICAR TIPO DE TRIÂNGULO",
            "4. CALCULAR PERÍMETRO",
            "5. CALCULAR ÁREA"
        };

        for (String label : buttonLabels) {
            CustomButton button = new CustomButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height + 10));
            button.addActionListener(this::handleMenuAction);
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        
        JPanel leftDecoratorPanel = new JPanel();
        leftDecoratorPanel.setBackground(panelColor);
        leftDecoratorPanel.setLayout(new BoxLayout(leftDecoratorPanel, BoxLayout.Y_AXIS));
        leftDecoratorPanel.setPreferredSize(new Dimension(40,0));
        leftDecoratorPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,2, Color.GRAY));

        String[] decoSymbols = {"|", "-", "-", "-", "+", "\u25A1"};
        for (String sym : decoSymbols) {
            JLabel decoLabel = new JLabel(sym, SwingConstants.CENTER);
            decoLabel.setFont(new Font("Arial", Font.BOLD, 20));
            decoLabel.setForeground(Color.WHITE);
            decoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftDecoratorPanel.add(Box.createVerticalGlue());
            leftDecoratorPanel.add(decoLabel);
        }
        leftDecoratorPanel.add(Box.createVerticalGlue());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(bgColor);
        bottomPanel.setBorder(new EmptyBorder(10, 50, 20, 50));
        feedbackLabel = new JLabel("Selecionar uma opção: <");
        feedbackLabel.setFont(labelFont);
        feedbackLabel.setForeground(buttonTextColor);
        bottomPanel.add(feedbackLabel);

        add(topPanel, BorderLayout.NORTH);
        add(leftDecoratorPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    // Método privado para atualizar o texto do rótulo de feedback
    private void updateFeedbackLabel(String message) {
        feedbackLabel.setText(message.length() > 60 ? message.substring(0, 57) + "..." : message);
    }

    // Método para tratar as ações dos botões do menu principal
    private void handleMenuAction(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText(); // Obtém o texto do botão clicado
        updateFeedbackLabel(command.split("\\. ")[1] + " selecionado..."); // Atualiza feedback com a ação

        try { // Bloco try-catch para lidar com exceções
            if (command.startsWith("1.")) { // Se "1. CADASTRAR LADOS"
                cadastrarLados(); // Chama o método correspondente
            } else if (command.startsWith("2.")) { // Se "2. VERIFICAR LADOS CADASTRADOS"
                verificarLadosCadastrados(); // Chama o método correspondente
            } else { // Para as outras opções (3, 4, 5) que requerem um triângulo selecionado
                // Verifica se um triângulo está atualmente selecionado
                // Agora usa 'triangulosDaSessao' em vez de 'gerenciadorTriangulos.getTriangulosCadastrados()'
                if (trianguloAtual == null && !triangulosDaSessao.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, selecione um triângulo em 'Verificar Lados Cadastrados' primeiro.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    verificarLadosCadastrados(); // Abre a tela de seleção
                    return; // Interrompe para o usuário selecionar
                } else if (trianguloAtual == null) { // Se nenhum triângulo está ativo
                     JOptionPane.showMessageDialog(this, "Nenhum triângulo cadastrado ou selecionado. Cadastre um triângulo primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return; // Interrompe
                }

                // Se um triângulo está ativo, prossegue
                if (command.startsWith("3.")) { // Verificar Tipo de Triângulo
                    String tipo = verificador.verificaTipo(trianguloAtual); // Calcula e define o tipo no objeto 'trianguloAtual'
                    // Não há mais necessidade de chamar gerenciadorTriangulos.salvarTriangulos() pois não há persistência.
                    // A alteração do tipo já está no objeto 'trianguloAtual' em memória.
                    JOptionPane.showMessageDialog(this, "Tipo do triângulo selecionado: " + tipo, "Tipo de Triângulo", JOptionPane.INFORMATION_MESSAGE);
                    updateFeedbackLabel("Tipo: " + tipo + " ("+ trianguloAtual.toString() +")");
                } else if (command.startsWith("4.")) { // Calcular Perímetro
                    double perimetro = verificador.calculaPerimetro(trianguloAtual);
                    JOptionPane.showMessageDialog(this, "Perímetro do triângulo selecionado: " + String.format("%.2f", perimetro), "Perímetro", JOptionPane.INFORMATION_MESSAGE);
                    updateFeedbackLabel("Perímetro: " + String.format("%.2f", perimetro) + " ("+ trianguloAtual.toString() +")");
                } else if (command.startsWith("5.")) { // Calcular Área
                    double area = verificador.calculaArea(trianguloAtual);
                    if (area > 0) {
                        JOptionPane.showMessageDialog(this, "Área do triângulo selecionado: " + String.format("%.2f", area), "Área", JOptionPane.INFORMATION_MESSAGE);
                        updateFeedbackLabel("Área: " + String.format("%.2f", area) + " ("+ trianguloAtual.toString() +")");
                    } else {
                         JOptionPane.showMessageDialog(this, "Não foi possível calcular a área (lados podem não formar um triângulo válido).", "Erro de Cálculo", JOptionPane.ERROR_MESSAGE);
                         updateFeedbackLabel("Erro ao calcular área.");
                    }
                }
            }
        } catch (Exception ex) { // Captura exceções inesperadas
            JOptionPane.showMessageDialog(this, "Ocorreu um erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            updateFeedbackLabel("Erro: " + ex.getMessage());
        }
    }

    // Método para cadastrar os lados de um novo triângulo
    private void cadastrarLados() {
        JTextField lado1Field = new JTextField(5);
        JTextField lado2Field = new JTextField(5);
        JTextField lado3Field = new JTextField(5);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Lado 1:"));
        panel.add(lado1Field);
        panel.add(new JLabel("Lado 2:"));
        panel.add(lado2Field);
        panel.add(new JLabel("Lado 3:"));
        panel.add(lado3Field);

        int result = JOptionPane.showConfirmDialog(this, panel, "Cadastrar Lados do Triângulo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) { // Se o usuário clicou "OK"
            try {
                double l1 = conversor.stringToDouble(lado1Field.getText());
                double l2 = conversor.stringToDouble(lado2Field.getText());
                double l3 = conversor.stringToDouble(lado3Field.getText());

                Triangulo novoTriangulo = new Triangulo(l1, l2, l3); // Cria o triângulo (validações internas)
                // Adiciona o novo triângulo diretamente à lista 'triangulosDaSessao' que pertence a esta instância da GUI.
                triangulosDaSessao.add(novoTriangulo);
                trianguloAtual = novoTriangulo; // Define o novo triângulo como o atual
                
                verificador.verificaTipo(trianguloAtual); // Calcula e define o tipo do novo triângulo
                // Não há mais chamada a gerenciadorTriangulos.adicionarTriangulo() ou salvarTriangulos().

                JOptionPane.showMessageDialog(this, "Triângulo cadastrado com sucesso!\n" + novoTriangulo.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                updateFeedbackLabel("Novo triângulo cadastrado e selecionado: " + trianguloAtual.toString());

            } catch (IllegalArgumentException e) { // Captura erros de validação dos lados
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                updateFeedbackLabel("Erro ao cadastrar: " + e.getMessage());
            } catch (Exception e) { // Captura outros erros (ex: conversão)
                JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, insira números.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                updateFeedbackLabel("Erro de entrada nos lados.");
            }
        }
    }

    // Método para exibir os triângulos cadastrados na sessão atual e permitir seleção/remoção
    private void verificarLadosCadastrados() {
        // Usa a lista 'triangulosDaSessao' diretamente.
        if (triangulosDaSessao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum triângulo cadastrado nesta sessão.", "Lados Cadastrados", JOptionPane.INFORMATION_MESSAGE);
            updateFeedbackLabel("Nenhum triângulo cadastrado nesta sessão.");
            return; // Encerra se a lista estiver vazia
        }

        DefaultListModel<String> listModel = new DefaultListModel<>(); // Modelo para a JList
        // Itera sobre a lista de triângulos da sessão
        for (int i = 0; i < triangulosDaSessao.size(); i++) {
            Triangulo t = triangulosDaSessao.get(i); // Pega o triângulo atual da lista
            if (t.getTipo() == null) { // Se o tipo ainda não foi definido
                 verificador.verificaTipo(t); // Calcula e define o tipo (modifica o objeto 't' na lista 'triangulosDaSessao')
            }
             listModel.addElement((i + 1) + ". " + t.toString()); // Adiciona à JList
        }
        // Não há mais chamada a gerenciadorTriangulos.salvarTriangulos() aqui.
        
        JList<String> listaTriangulosUI = new JList<>(listModel);
        listaTriangulosUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaTriangulosUI);
        scrollPane.setPreferredSize(new Dimension(350, 150));

        JButton removerButton = new JButton("Remover Selecionado");
        removerButton.addActionListener(e -> { // Lambda para o evento de clique do botão remover
            int selectedIndex = listaTriangulosUI.getSelectedIndex(); // Índice do item selecionado
            if (selectedIndex != -1) { // Se algo está selecionado
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Tem certeza que deseja remover o triângulo selecionado?", 
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) { // Se o usuário confirmar
                    // Obtém o triângulo a ser removido da lista da sessão
                    Triangulo tParaRemover = triangulosDaSessao.get(selectedIndex);
                    
                    // Remove o triângulo diretamente da lista 'triangulosDaSessao'
                    triangulosDaSessao.remove(tParaRemover);
                    listModel.remove(selectedIndex); // Remove da JList (interface)
                    
                    // Lógica para atualizar 'trianguloAtual' se o removido era o atual
                    if (tParaRemover.equals(trianguloAtual)) {
                        trianguloAtual = null; // Limpa o triângulo atual
                        // Se a lista da sessão não estiver vazia, tenta selecionar outro
                        if (!triangulosDaSessao.isEmpty()) {
                             int newIndex = Math.max(0, selectedIndex -1); // Tenta pegar o anterior ou o primeiro
                             if(triangulosDaSessao.size() > newIndex) { // Se o índice é válido
                                trianguloAtual = triangulosDaSessao.get(newIndex);
                             } else if (!triangulosDaSessao.isEmpty()){ // Se não, mas a lista não está vazia, pega o primeiro
                                 trianguloAtual = triangulosDaSessao.get(0);
                             }
                        }
                    }
                     updateFeedbackLabel(trianguloAtual != null ? "Triângulo selecionado: " + trianguloAtual.toString() : "Nenhum triângulo selecionado.");
                    JOptionPane.showMessageDialog(this, "Triângulo removido.", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                }
            } else { // Se nada foi selecionado para remover
                JOptionPane.showMessageDialog(this, "Selecione um triângulo para remover.", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        JPanel panelDialog = new JPanel(new BorderLayout(5,5));
        panelDialog.add(new JLabel("Triângulos cadastrados nesta sessão. Selecione um para operações:"), BorderLayout.NORTH);
        panelDialog.add(scrollPane, BorderLayout.CENTER);
        panelDialog.add(removerButton, BorderLayout.SOUTH);

        int result = JOptionPane.showConfirmDialog(this, panelDialog, "Triângulos Cadastrados",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) { // Se o usuário clicou "OK" (para selecionar)
            int selectedIndex = listaTriangulosUI.getSelectedIndex();
            // Verifica se um item foi selecionado E se o índice é válido para a lista 'triangulosDaSessao'
            if (selectedIndex != -1 && selectedIndex < triangulosDaSessao.size()) {
                trianguloAtual = triangulosDaSessao.get(selectedIndex); // Define o selecionado como 'trianguloAtual'
                if (trianguloAtual.getTipo() == null) { // Se o tipo não estiver definido
                    verificador.verificaTipo(trianguloAtual); // Calcula e define (modifica o objeto na lista)
                    // Não há salvamento em arquivo.
                }
                JOptionPane.showMessageDialog(this, "Triângulo selecionado: " + trianguloAtual.toString(), "Seleção", JOptionPane.INFORMATION_MESSAGE);
                updateFeedbackLabel("Triângulo selecionado: " + trianguloAtual.toString());
            } else if (trianguloAtual != null) { // Se nada foi selecionado, mas já havia um triângulo atual
                updateFeedbackLabel("Mantido triângulo anterior: " + trianguloAtual.toString());
            } else { // Se nada foi selecionado e não havia triângulo atual
                 updateFeedbackLabel("Nenhum triângulo selecionado.");
            }
        }
    }
}