package paneldomotico.dominio;

/**
 * Implementacion de una persiana. 
 * Una persiana esta caracterizada por su nivel de altura de 0 a 100 
 * siendo 0 completamente bajada y 100 completamente subida
 * 
 * @author aleniet
 * @author davmele
 */
public class Persiana {
    private int nivel;
    
    /**
     * Inicializador de una persiana completamente bajada
     */
    public Persiana(){
        nivel = 0;
    }
    
    /**
     * Inicializador de una persiana a un nivel dado
     * 
     * @param nivel el nivel de altura de la persiana
     * @throws IllegalArgumentException si el nivel dado no esta en el rango [0,100]
     */
    public Persiana(int nivel){
        if(nivel < 0 || nivel > 100){
            throw new IllegalArgumentException("Nivel fuera de rango");
        }
        this.nivel = nivel;
    }
    
    /**
     * Establece el nivel de altura de la persiana
     * 
     * @param nivel el nivel de altura de la persiana
     * @throws IllegalArgumentException si el nivel dado no esta en el rango [0,100]
     */
    public void setNivel(int nivel){
        if(nivel < 0 || nivel > 100){
            throw new IllegalArgumentException("Nivel fuera de rango");
        }
        this.nivel = nivel;
    }
    
    /**
     * Devuelve el nivel de altura de la persiana
     * 
     * @return un int con el nivel
     */
    public int getNivel(){
        return nivel;
    }
}
