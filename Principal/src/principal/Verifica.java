package principal;

/** 
 * Classe para realizar verificações e cálculos sobre um Triângulo.
 */ 
public class Verifica { 

     
    public String verificaTipo(Triangulo t) { 
        if (!t.isValido()) { 
             return "Triângulo Inválido ou não cadastrado"; 
        } 

        double l1 = t.getLado1(); 
        double l2 = t.getLado2(); 
        double l3 = t.getLado3(); 
        String tipoCalculado; 

        if (l1 == l2 && l2 == l3) { 
            tipoCalculado = "Equilátero"; 
        } else if (l1 == l2 || l1 == l3 || l2 == l3) { 
            tipoCalculado = "Isósceles"; 
        } else { 
            tipoCalculado = "Escaleno"; 
        } 
        
        t.setTipo(tipoCalculado); 
        return tipoCalculado; 
    } 

    
    public double calculaArea(Triangulo t) { 
         if (!t.isValido()) { 
             return -1.0; 
        } 
        double l1 = t.getLado1(); 
        double l2 = t.getLado2(); 
        double l3 = t.getLado3(); 
        double s = (l1 + l2 + l3) / 2.0; 
        double area = Math.sqrt(s * (s - l1) * (s - l2) * (s - l3)); 
        return area; 
    } 

    
    public double calculaPerimetro(Triangulo t) { 
         if (!t.isValido()) { 
             return -1.0; 
        } 
        return t.getLado1() + t.getLado2() + t.getLado3(); 
    } 
}
