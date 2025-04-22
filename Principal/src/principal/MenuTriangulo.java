package principal;

/** // 
 * Classe que gerencia o menu principal da aplicação de cálculo de triângulos,
 * interagindo com o usuário através de interface gráfica (JOptionPane) e
 * coordenando as ações com as outras classes (Triangulo, Verifica, Conversor, IO).
 */ 
public class MenuTriangulo { 

    // -- Atributos --
    // Declaração das instâncias das classes auxiliares que o MenuTriangulo vai usar.
    // São 'private' porque só devem ser acessadas de dentro desta classe.
    private EntradaSaidaDados io;         // Objeto para entrada e saída de dados gráficos.
    private ConversorNumeros conversor;    // Objeto para converter strings em números.
    private Triangulo meuTriangulo;      // Objeto para armazenar os dados do triângulo atual.
    private Verifica verificador;        // Objeto para realizar cálculos e verificações no triângulo.
    private int opcao;                   // Guarda a opção do menu escolhida pelo usuário.

    // -- Construtor --
    /** //
     * Construtor da classe MenuTriangulo.
     * Inicializa todos os objetos auxiliares necessários para o funcionamento do menu.
     */ // 
    public MenuTriangulo() { // Declaração do construtor público.
        // Inicializa (cria) os objetos declarados nos atributos.
        // 'this' é opcional aqui, mas reforça que estamos inicializando os atributos da instância.
        this.io = new EntradaSaidaDados();         // Cria a instância para IO gráfico.
        this.conversor = new ConversorNumeros();    // Cria a instância do conversor.
        this.meuTriangulo = new Triangulo();      // Cria a instância do triângulo (inicialmente vazio/inválido).
        this.verificador = new Verifica();        // Cria a instância do verificador.
        this.opcao = -1;                           // Inicializa a opção com um valor inválido para garantir entrada no loop.
    } // Fim do construtor.

    // -- Método Principal de Execução do Menu --
    /** //.
     * Executa o loop principal do menu, apresentando as opções ao usuário,
     * lendo a escolha e direcionando para a ação correspondente até que o usuário escolha sair.
     */ // 
    public void executarMenu() { // Declaração do método público que contém o loop do menu.
        // Inicia um loop 'do-while' que continuará executando enquanto a 'opcao' for diferente de 0.
        do { 
            // Chama o método privado para exibir o menu e obter a opção do usuário.
            this.solicitarOpcaoMenu(); 

            // Inicia uma estrutura 'switch' para tratar a opção escolhida.
            switch (this.opcao) { 
                case 1: // Se a opção for 1...
                    this.cadastrarLadosTriangulo(); // Chama o método privado para cadastrar lados.
                    break; // Sai do switch.

                case 2: // Se a opção for 2...
                    this.verificarLadosCadastrados(); // Chama o método privado para verificar lados.
                    break; // Sai do switch.

                case 3: // Se a opção for 3...
                    this.verificarTipoTriangulo(); // Chama o método privado para verificar tipo.
                    break; // Sai do switch.

                case 4: // Se a opção for 4...
                    this.calcularPerimetroTriangulo(); // Chama o método privado para calcular perímetro.
                    break; // Sai do switch.

                case 5: // Se a opção for 5...
                    this.calcularAreaTriangulo(); // Chama o método privado para calcular área.
                    break; // Sai do switch.

                case 0: // Se a opção for 0 (Sair)...
                    this.io.saidaDados("Saindo do programa..."); // Mostra mensagem de despedida.
                    break; // Sai do switch (e consequentemente do loop).

                default: // Se a opção for qualquer outro número...
                    // Verifica se a opção não foi -1 (caso o usuário tenha cancelado a caixa de diálogo inicial)
                    if(this.opcao != -1) { 
                       this.io.saidaDadosErro("Opção inválida! Tente novamente."); // Mostra erro de opção inválida.
                    }
                    // Se a opção for -1 (cancelou), não mostra mensagem de erro, apenas volta ao loop.
                    break; // Sai do switch.
            } // Fim do switch.

        // Condição do loop: continuar enquanto 'opcao' for diferente de 0.
        } while (this.opcao != 0); 
    } // Fim do método executarMenu.

    // -- Métodos Privados Auxiliares para cada Ação do Menu --

    /** //
     * Exibe o menu de opções para o usuário e lê a opção digitada.
     * Trata o caso de cancelamento ou entrada inválida.
     */ // 
    private void solicitarOpcaoMenu() { // Declaração do método privado para obter a opção.
        // Monta a string do menu.
         String menuTexto = """
                           --- Menu Calculadora de Triângulos ---
                           1 - Cadastrar Lados
                           2 - Verificar Lados Cadastrados
                           3 - Verificar Tipo de Triângulo
                           4 - Calcular Perímetro
                           5 - Calcular Área
                           0 - Sair
                           --------------------------------------
                           Digite sua opção:
                           """;
                           
        // Pede a opção ao usuário usando a caixa de diálogo gráfica.
        String opcaoStr = this.io.entradaDados(menuTexto); 

        // Verifica se o usuário cancelou (entrada nula).
        if (opcaoStr == null) { 
            this.opcao = 0; // Se cancelou, define a opção como 0 (Sair).
        } else { 
            // Se não cancelou, tenta converter a string para inteiro.
            // O resultado (número ou 0 em caso de erro) é armazenado no atributo 'opcao'.
            this.opcao = this.conversor.stringToInt(opcaoStr); 
            // Se a conversão resultou em 0, mas a string não era "0", foi um erro de conversão.
            // Neste caso, para evitar sair do programa, mudamos para uma opção inválida (-1).
            if (this.opcao == 0 && !opcaoStr.trim().equals("0")) {
                this.opcao = -1; // Marca como inválida para o loop continuar e mostrar erro.
            }
        }
    } 

    /** // .
     * Solicita os três lados do triângulo ao usuário, tenta cadastrá-los
     * e informa o resultado da operação.
     */ // 
    private void cadastrarLadosTriangulo() { // Declaração do método privado para cadastro.
        // Pede o lado 1.
        String lado1Str = this.io.entradaDados("Digite o lado 1:"); 
        if (lado1Str == null) return; // Se cancelar, interrompe o cadastro.

        // Pede o lado 2.
        String lado2Str = this.io.entradaDados("Digite o lado 2:"); 
        if (lado2Str == null) return; // Se cancelar, interrompe o cadastro.

        // Pede o lado 3.
        String lado3Str = this.io.entradaDados("Digite o lado 3:"); 
        if (lado3Str == null) return; // Se cancelar, interrompe o cadastro.

        // Converte as strings para double.
        double l1 = this.conversor.stringToDouble(lado1Str); 
        double l2 = this.conversor.stringToDouble(lado2Str); 
        double l3 = this.conversor.stringToDouble(lado3Str); 

        // Verifica se as conversões foram válidas e os números são positivos.
        if (l1 <= 0 || l2 <= 0 || l3 <= 0) { 
             this.io.saidaDadosErro("Erro: Entrada inválida para um ou mais lados. Digite apenas números positivos.");
        } else {
             // Tenta cadastrar os lados no objeto 'meuTriangulo'.
             if (this.meuTriangulo.cadastrarLados(l1, l2, l3)) { 
                 this.io.saidaDados("Lados cadastrados com sucesso!"); 
             } else { 
                 this.io.saidaDadosErro("Erro: Os valores informados ("+l1+", "+l2+", "+l3+") não formam um triângulo válido."); 
             } 
        }
    } // Fim do método cadastrarLadosTriangulo.

    /** //
     * Verifica se um triângulo válido está cadastrado e exibe seus lados.
     * Caso contrário, informa o usuário.
     */ // 
    private void verificarLadosCadastrados() { // Declaração do método privado para verificar lados.
        // Verifica se o triângulo atual é válido.
        if (this.meuTriangulo.isValido()) { 
            // Monta a string com as informações dos lados.
            String ladosInfo = "--- Lados Atuais ---\n" + 
                               "Lado 1: " + this.meuTriangulo.getLado1() + "\n" +
                               "Lado 2: " + this.meuTriangulo.getLado2() + "\n" +
                               "Lado 3: " + this.meuTriangulo.getLado3(); 
            // Exibe as informações.
            this.io.saidaDados(ladosInfo); 
        } else { 
            // Informa que não há triângulo válido.
            this.io.saidaDadosErro("Nenhum triângulo válido cadastrado."); 
        } 
    } // Fim do método verificarLadosCadastrados.

    /** // 
     * Verifica e exibe o tipo do triângulo cadastrado (Equilátero, Isósceles, Escaleno).
     * Informa erro se nenhum triângulo válido estiver cadastrado.
     */ //
    private void verificarTipoTriangulo() { // Declaração do método privado para verificar tipo.
        // Verifica se o triângulo é válido.
        if (this.meuTriangulo.isValido()) { 
            // Chama o método de verificação e obtém o tipo.
            String tipo = this.verificador.verificaTipo(this.meuTriangulo); 
            // Exibe o tipo.
            this.io.saidaDados("--- Tipo do Triângulo ---\nO triângulo é: " + tipo); 
        } else { 
            // Informa que não há triângulo válido.
            this.io.saidaDadosErro("Cadastre um triângulo válido primeiro."); 
        } 
    } // Fim do método verificarTipoTriangulo.

    /** // 
     * Calcula e exibe o perímetro do triângulo cadastrado.
     * Informa erro se nenhum triângulo válido estiver cadastrado.
     */ //
    private void calcularPerimetroTriangulo() { // Declaração do método privado para calcular perímetro.
        // Verifica se o triângulo é válido.
        if (this.meuTriangulo.isValido()) { 
            // Calcula o perímetro.
            double perimetro = this.verificador.calculaPerimetro(this.meuTriangulo); 
            // Exibe o perímetro formatado.
            this.io.saidaDados(String.format("--- Perímetro ---\nO perímetro é: %.2f", perimetro)); 
        } else { 
            // Informa que não há triângulo válido.
            this.io.saidaDadosErro("Cadastre um triângulo válido primeiro."); 
        } 
    } // Fim do método calcularPerimetroTriangulo.

    /** // 
     * Calcula e exibe a área do triângulo cadastrado.
     * Informa erro se nenhum triângulo válido estiver cadastrado.
     */ // 
    private void calcularAreaTriangulo() { // Declaração do método privado para calcular área.
        // Verifica se o triângulo é válido.
        if (this.meuTriangulo.isValido()) { 
            // Calcula a área.
            double area = this.verificador.calculaArea(this.meuTriangulo); 
            // Exibe a área formatada.
            this.io.saidaDados(String.format("--- Área ---\nA área é: %.2f", area)); 
        } else { 
            // Informa que não há triângulo válido.
            this.io.saidaDadosErro("Cadastre um triângulo válido primeiro."); 
        } 
    } // Fim do método calcularAreaTriangulo.

} // Fim da classe MenuTriangulo.
