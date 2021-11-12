package paneldomotico.dominio;

import java.awt.Color;

/**
 * Implementacion de una luz.
 * Una luz se caracteriza por su intensidad, color y estado.
 * La intensidad y el color son modificables solo si la luz esta encendida.
 * 
 * @author davmele
 * @author aleniet
 */
public class Luz {
    private int intensidad;
    private Color color;
    private boolean estado;  //true si la luz esta encendida; false en caso contrario
    
    /**
     * Inicializador sin argumentos.
     * Inicializa una luz apagada con color blanco e intensidad 100
     */
    public Luz(){
        intensidad = 100;
        color = Color.WHITE;
        estado = false;
    }
    
    /**
     * Inicializador con argumentos.
     * Inicializa una luz encendida con la intensidad y color especificados
     * 
     * @param intensidad la intensidad de la luz
     * @param color el color de la luz
     * Ver excepciones en {@link Luz#setIntensidad(int)} y {@link Luz#setColor(java.awt.Color) }
     */
    public Luz(int intensidad, Color color){
        estado = true;
        setIntensidad(intensidad);
        setColor(color);
    }
    
    /**
     * Devuelve la intensidad de la luz
     * 
     * @return un int con la intensidad
     */
    public int getIntensidad(){
        return intensidad;
    }
    
     /**
     * Devuelve el color de la luz
     * 
     * @return un Color con el color
     */
    public Color getColor(){
        return color;
    }
    
    
    
    /**
     * Informa sobre si la luz esta encendida
     * 
     * @return true si lo esta; false en caso contrario
     */
    public boolean encendida(){
        return estado;
    }
    
    /**
     * Informa sobre si la luz esta apagada
     * 
     * @return true si lo esta; false en caso contrario
     */
    public boolean apagada(){
        return !encendida();
    }
     
    /**
     * Modifica el color de la luz
     * 
     * @param color el nuevo color de la luz
     * @throws NullPointerException si el color dado es igual a null
     * @throws IllegalStateException si la luz esta apagada
     */
    public void setColor(Color color){
        if(color == null){
            throw new NullPointerException("Color de luz igual a null");
        }
        if(apagada()){
            throw new IllegalStateException("Luz apagada");
        }
        this.color = color;
    }
    
    /**
     * Modifica la intensidad de la luz
     * 
     * @param intensidad la nueva intensidad en porcentaje
     * @throws IllegalArgumentException si la intensidad no esta en el rango [0,100]
     * @throws IllegalStateException si la luz esta apagada
     */
    public void setIntensidad(int intensidad){
        if(intensidad < 0 || intensidad > 100){
            throw new IllegalArgumentException("Intensidad de luz fuera de rango");
        }
        if(apagada()){
            throw new IllegalStateException("Luz apagada");
        }
        this.intensidad = intensidad;
    }
    
    /**
     * Enciende la luz
     */
    public void encender(){
        estado = true;
    }
    
    /**
     * Apaga la luz
     */
    public void apagar(){
        estado = false;
    }
    
    /**
     * Informa sobre si this y el objecto dado son iguales
     * Se considera que dos luces son iguales si ambas estan apagadas o si todas sus caracteristicas son iguales
     * 
     * @param obj el objeto especificada
     * @return true si son iguales; false en caso contrario
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Luz)){
            return false;
        }
        Luz luz = (Luz) obj;
        return (apagada() && luz.apagada()) || (getIntensidad() == luz.getIntensidad() && getColor().equals(luz.getColor()) && encendida() && luz.encendida());    
    }
    
    /**
     * Devuelve una representacion de la luz
     * 
     * @return un String con la representacion
     */
    @Override
    public String toString(){
        return "Encendida: "+encendida()+"  Intensidad: "+getIntensidad()+"  Color: "+getColor();
    }
}
