package principal; // Define que esta classe pertence ao pacote 'principal'

import java.io.Serializable; // Importa a interface Serializable para permitir que objetos desta classe sejam serializados (salvos em arquivo/transmitidos)

public class Triangulo implements Serializable { // Declaração da classe pública Triangulo, que implementa a interface Serializable
    // serialVersionUID é usado durante a desserialização para verificar se o remetente e o receptor de um objeto serializado
    // carregaram classes para esse objeto que são compatíveis em relação à serialização.
    private static final long serialVersionUID = 1L;

    // Atributos privados para armazenar os comprimentos dos três lados do triângulo
    private double lado1;
    private double lado2;
    private double lado3;
    // Atributo privado para armazenar o tipo do triângulo (ex: Equilátero, Isósceles, Escaleno)
    private String tipo;

    // Construtor padrão (sem argumentos). Útil para algumas frameworks ou quando a inicialização dos lados é feita depois.
    public Triangulo() {}

    // Construtor que recebe os três lados como argumentos e já os cadastra
    public Triangulo(double lado1, double lado2, double lado3) {
        // Chama o método cadastrarLados para definir e validar os lados
        cadastrarLados(lado1, lado2, lado3);
    }

    // Método público para cadastrar (definir) os lados do triângulo e realizar validações
    public void cadastrarLados(double lado1, double lado2, double lado3) {
        // Verifica se algum dos lados é menor ou igual a zero
        if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0) {
            // Se for, lança uma IllegalArgumentException informando que os lados devem ser positivos
            throw new IllegalArgumentException("Os lados do triângulo devem ser positivos.");
        }
        // Verifica a condição de existência de um triângulo:
        // a soma de quaisquer dois lados deve ser maior que o terceiro lado.
        if (!((lado1 + lado2 > lado3) && (lado1 + lado3 > lado2) && (lado2 + lado3 > lado1))) {
            // Se não formarem um triângulo, lança uma IllegalArgumentException
            throw new IllegalArgumentException("Os lados fornecidos não formam um triângulo válido.");
        }
        // Se todas as validações passarem, atribui os valores aos atributos da classe
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        // Inicializa o tipo como null, pois ele será determinado posteriormente pela classe Verifica
        this.tipo = null;
    }

    // Método getter público para retornar o valor do lado1
    public double getLado1() {
        return lado1;
    }

    // Método getter público para retornar o valor do lado2
    public double getLado2() {
        return lado2;
    }

    // Método getter público para retornar o valor do lado3
    public double getLado3() {
        return lado3;
    }

    // Método getter público para retornar o tipo do triângulo
    public String getTipo() {
        return tipo;
    }

    // Método setter público para definir o tipo do triângulo (geralmente chamado pela classe Verifica)
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Sobrescreve o método toString() da classe Object para fornecer uma representação em String do objeto Triangulo
    @Override
    public String toString() {
        // Formata a string para exibir os lados com duas casas decimais
        // Se o tipo do triângulo já foi definido, ele também é incluído na string
        return String.format("Lados: %.2f, %.2f, %.2f", lado1, lado2, lado3) + (tipo != null ? " - Tipo: " + tipo : "");
    }
}