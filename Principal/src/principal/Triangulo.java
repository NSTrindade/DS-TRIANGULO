package principal;

/** 
 * Classe para representar os dados de um Triângulo.
 */ 
public class Triangulo { 

    private double lado1; 
    private double lado2; 
    private double lado3; 
    private String tipo; 

    public Triangulo() { 
        this.lado1 = 0; 
        this.lado2 = 0; 
        this.lado3 = 0; 
        this.tipo = "Não cadastrado"; 
    } 

    /** 
     * Tenta definir os valores dos lados.
     * @return true se os lados formarem um triângulo válido, false caso contrário. 
     */ 
    public boolean cadastrarLados(double l1, double l2, double l3) { 
        boolean cond1 = (l1 + l2) > l3; 
        boolean cond2 = (l1 + l3) > l2; 
        boolean cond3 = (l2 + l3) > l1; 
        boolean positivos = (l1 > 0 && l2 > 0 && l3 > 0); 

        if (positivos && cond1 && cond2 && cond3) { 
            this.lado1 = l1; 
            this.lado2 = l2; 
            this.lado3 = l3; 
            this.tipo = "Ainda não verificado"; 
            return true; 
        } else { 
            this.lado1 = 0; 
            this.lado2 = 0; 
            this.lado3 = 0; 
            this.tipo = "Inválido"; 
            return false; 
        } 
    } 

    // Getters
    public double getLado1() { return lado1; } 
    public double getLado2() { return lado2; } 
    public double getLado3() { return lado3; } 
    public String getTipo() { return tipo; } 
    
    // Setter
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    } 
    
    /** 
     * Verifica se os lados foram cadastrados e formam um triângulo válido.
     * @return true se o triângulo tiver lados válidos (maiores que 0), false caso contrário. 
     */ 
    public boolean isValido() { 
        return this.lado1 > 0 && this.lado2 > 0 && this.lado3 > 0; 
    } 
}
