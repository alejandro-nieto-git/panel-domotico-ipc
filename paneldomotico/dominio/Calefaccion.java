package paneldomotico.dominio;

/**
 * Implementacion de una calefaccion.
 * Una calefaccion se caracteriza por su temperatura en grados Celsius.
 * La temperatura no puede superar un maximo de 30 ni un minimo de 12 grados.
 * 
 * @author aleniet
 * @author davmele
 */
public class Calefaccion {
    private double temperatura;
    
    /**
     * Inicializador de una calefaccion a la temperatura especificada
     * 
     * @param temperatura la temperatura de la calefaccion
     * @throws IllegalArgumentException si la temperatura esta fuera del rango [12,30]
     */
    public Calefaccion(double temperatura){
        if(temperatura < 12 || temperatura > 30){
            throw new IllegalArgumentException("Temperatura fuera de rango");
        }
        this.temperatura = temperatura;
    }
    
    /**
     * Establece la temperatura de la calefaccion
     * 
     * @param temperatura la temperatura de la calefaccion
     * @throws IllegalArgumentException si la temperatura esta fuera del rango [12,30]
     */
    public void setTemperatura(double temperatura){
        if(temperatura < 12 || temperatura > 30){
            throw new IllegalArgumentException("Temperatura fuera de rango");
        }
        this.temperatura = temperatura;
    }
    
    /**
     * Devuelve la temperatura de la calefaccion
     * 
     * @return un double con la temperatura
     */
    public double getTemperatura(){
        return temperatura;
    }
}
