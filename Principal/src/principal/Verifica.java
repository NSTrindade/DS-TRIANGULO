package principal; // Define que esta classe pertence ao pacote 'principal'

// Esta classe não precisa de imports adicionais se Triangulo está no mesmo pacote.

public class Verifica { // Declaração da classe pública Verifica, responsável por operações de verificação e cálculo sobre triângulos

    // Método público para verificar e retornar o tipo de um triângulo
    // Recebe um objeto Triangulo 't' como parâmetro
    public String verificaTipo(Triangulo t) {
        // Verifica se o objeto triângulo é nulo ou se não tem lados cadastrados (lado1 <= 0 é uma checagem simplificada)
        if (t == null || t.getLado1() <= 0) {
            return "Triângulo não cadastrado ou inválido."; // Retorna uma mensagem de erro
        }
        // Obtém os valores dos lados do triângulo
        double l1 = t.getLado1();
        double l2 = t.getLado2();
        double l3 = t.getLado3();

        String tipoTriangulo; // Variável para armazenar o tipo determinado
        // Verifica se todos os lados são iguais (Equilátero)
        if (l1 == l2 && l2 == l3) {
            tipoTriangulo = "Equilátero";
        // Verifica se pelo menos dois lados são iguais (Isósceles)
        } else if (l1 == l2 || l1 == l3 || l2 == l3) {
            tipoTriangulo = "Isósceles";
        // Se nenhum dos casos anteriores, todos os lados são diferentes (Escaleno)
        } else {
            tipoTriangulo = "Escaleno";
        }
        // Define o tipo determinado no objeto Triangulo 't'
        t.setTipo(tipoTriangulo);
        // Retorna o tipo do triângulo
        return tipoTriangulo;
    }

    // Método público para calcular a área de um triângulo usando a fórmula de Heron
    // Recebe um objeto Triangulo 't' como parâmetro
    public double calculaArea(Triangulo t) {
        // Verifica se o triângulo é nulo ou inválido
        if (t == null || t.getLado1() <= 0) {
             return 0; // Retorna 0 se o triângulo for inválido
        }
        // Obtém os valores dos lados
        double l1 = t.getLado1();
        double l2 = t.getLado2();
        double l3 = t.getLado3();

        // Calcula o semiperímetro (s)
        double s = (l1 + l2 + l3) / 2.0;
        // Validação adicional para a fórmula de Heron: o semiperímetro deve ser maior que cada lado individualmente.
        // Se s for menor ou igual a algum lado, a expressão sob a raiz quadrada será negativa ou zero (triângulo degenerado).
        if (s <= l1 || s <= l2 || s <= l3) {
            return 0; // Retorna 0 se não for possível calcular a área (ex: triângulo degenerado)
        }
        // Aplica a fórmula de Heron: Area = sqrt(s * (s-l1) * (s-l2) * (s-l3))
        return Math.sqrt(s * (s - l1) * (s - l2) * (s - l3));
    }

    // Método público para calcular o perímetro de um triângulo
    // Recebe um objeto Triangulo 't' como parâmetro
    public double calculaPerimetro(Triangulo t) {
        // Verifica se o triângulo é nulo ou inválido
         if (t == null || t.getLado1() <= 0) {
             return 0; // Retorna 0 se o triângulo for inválido
        }
        // O perímetro é a soma dos três lados
        return t.getLado1() + t.getLado2() + t.getLado3();
    }
}