package paneldomotico.dominio;

import java.util.ArrayList;
import java.awt.Color;

/**
 * Implementacion de una habitacion.
 * Una habitacion esta caracterizada por las luces que contiene, su calefaccion y sus persianas.
 * Las luces y persianas estan numeradas desde 1.
 * 
 * @author aleniet
 * @author davmele
 */
public class Habitacion {
    private ArrayList<Luz> luces;
    private Calefaccion calefaccion;
    private ArrayList<Persiana> persianas; 

    
    /**
     * Inicializador de una habitacion por defecto 
     * 
     * @param numLuces el numero de luces
     * @param numPersianas el numero de persianas
     * @param temperatura la temperatura de la calefaccion
     * @throws IllegalArgumentException si el numero de luces es negativo o 
     *                                  si el numero de persianas es negativo
     */
    public Habitacion(int numLuces, int numPersianas, double temperatura){
        if(numLuces < 0){
            throw new IllegalArgumentException("Numero negativo de luces");
        }
        if(numPersianas < 0){
            throw new IllegalArgumentException("Numero negativo de persianas");
        }
        
        luces = new ArrayList<>();
        for(int i = 0; i < numLuces; i++){
            luces.add(new Luz());
        }
        
        calefaccion = new Calefaccion(temperatura);
        
        persianas = new ArrayList<>();
        for(int i = 0; i < numPersianas; i++){
            persianas.add(new Persiana());
        }
    }
    
    /**
     * Inicializador con argumentos 
     * 
     * @param luces las luces de la habitacion
     * @param numPersianas el numero de persianas
     * @param temperatura la temperatura de la calefaccion
     * @throws NullPointerException si la lista dada es o contiene null
     * @throws IllegalArgumentException si el numero de persianas es negativo
     */
    public Habitacion(ArrayList<Luz> luces, int numPersianas, double temperatura){
        if(luces == null || luces.contains(null)){
            throw new NullPointerException("Alguna luz igual a null");
        }
        if(numPersianas < 0){
            throw new IllegalArgumentException("Numero negativo de persianas");
        }
        
        this.luces = new ArrayList<>();
        for(int i = 0; i < luces.size(); i++){
            this.luces.add(luces.get(i));
        }
        
        calefaccion = new Calefaccion(temperatura);
        
        persianas = new ArrayList<>();
        for(int i = 0; i < numPersianas; i++){
            persianas.add(new Persiana());
        }
    }
    
    /**
     * Devuelve el numero de persianas de la habitacion
     * 
     * @return un int con el numero de persianas
     */
    public int getNumPersianas(){
        return persianas.size();
    }
    
    /**
     * Devuelve el nivel de la persiana especificada
     * 
     * @param numPersiana el numero de persiana
     * @return un int con el nivel
     * @throws IllegalArgumentException si el numero dado no se corresponde con niguna persiana
     */
    public int getNivelPersiana(int numPersiana){
        if(numPersiana < 1 || numPersiana > getNumPersianas()){
            throw new IllegalArgumentException("Numero de persiana incorrecto");
        }
        return persianas.get(numPersiana-1).getNivel();
    }
    
    /**
     * Modifica el nivel de la persiana especificada
     * 
     * @param numPersiana el numero de persiana
     * @param nivel el nuevo nivel de la persiana
     * @throws IllegalArgumentException si el numero dado no se corresponde con niguna persiana o 
     *                                  si el nivel dado no esta en el rango [0,100]
     */
    public void setNivelPersiana(int numPersiana, int nivel){
        if(numPersiana < 1 || numPersiana > getNumPersianas()){
            throw new IllegalArgumentException("Numero de persiana incorrecto");
        }
        if(nivel < 0 || nivel > 100){
            throw new IllegalArgumentException("Nivel de persiana fuera de rango");
        }
        persianas.get(numPersiana-1).setNivel(nivel);
    }
    
    /**
     * Devuelve la temperatura de la calefaccion de la habitacion
     * 
     * @return un double con la temperatura
     */
    public double getTemperatura(){
        return calefaccion.getTemperatura();
    }
    
    /**
     * Modifica la temperatura de la calefaccion
     * 
     * @param temperatura la nueva temperatura
     */
    public void setTemperatura(double temperatura){
        calefaccion.setTemperatura(temperatura);
    }
    
    
    /**
     * Devuelve el numero de luces de la habitacion
     * 
     * @return un int con el numero de luces
     */
    public int getNumLuces(){
        return luces.size();
    }
    
    /**
     * Enciende la luz indicada
     * 
     * @param n numero de luz a encender
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     */
    public void encender(int n){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        
        luces.get(n-1).encender();
    }
    
    /**
     * Apaga la luz indicada
     * 
     * @param n numero de luz a apagar
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     */
    public void apagar(int n){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        
        luces.get(n-1).apagar();
    }
    
    /**
     * Apaga todas las luces de la habitacion
     */
    public void apagarTodas(){
        for(int i = 0; i < getNumLuces(); i++){
            luces.get(i).apagar();
        }
    }
    
    /**
     * Enciende todas las luces de la habitacion
     */
    public void encenderTodas(){
        for(int i = 0; i < getNumLuces(); i++){
            luces.get(i).encender();
        }
    }
    
    /**
     * Modifica la intensidad de la luz indicada
     * 
     * @param n numero de luz
     * @param intensidad la nueva intensidad
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     * Ver mas excepciones en {@link Luz#setIntensidad(int) } 
     */
    public void setIntensidad(int n, int intensidad){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        
        luces.get(n-1).setIntensidad(intensidad);
    }
    
    /**
     * Modifica la intensidad de todas las luces de la habitacion
     * 
     * @param intensidad la nueva intensidad
     * Ver excepciones en {@link Habitacion#setIntensidad(int,int) }
     */
    public void setIntensidad(int intensidad){
        for(int i = 1; i <= getNumLuces(); i++){
            setIntensidad(i, intensidad);
        }
    }
    
    /**
     * Devuelve la intensidad de la luz indicada
     * 
     * @param n numero de luz
     * @return un int con la intensidad
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     */
    public int getIntensidad(int n){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        
        return luces.get(n-1).getIntensidad();
    }
    
    /**
     * Modifica el color de la luz indicada
     * 
     * @param n numero de luz
     * @param color el nuevo color
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     * Ver mas excepciones en {@link Luz#setColor(java.awt.Color)} 
     */
    public void setColor(int n, Color color){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        
        luces.get(n-1).setColor(color);
    }
    
    /**
     * Modifica el color de todas las luces de la habitacion
     * 
     * @param color el nuevo color
     * Ver excepciones en {@link Habitacion#setColor(int,java.awt.Color)}
     */
    public void setColor(Color color){
        for(int i = 1; i <= getNumLuces(); i++){
            setColor(i, color);
        }
    }
    
    /**
     * Modifica el color de la luz indicada
     * 
     * @param n numero de luz
     * @return un Color con el color de la luz
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     */
    public Color getColor(int n){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        
        return luces.get(n-1).getColor();
    }
    
    /**
     * Incluye una nueva luz en la habitacion
     * 
     * @param luz la nueva luz
     * @throws NullPointerException si la luz es null
     */
    public void addLuz(Luz luz){
        if(luz == null){
            throw new NullPointerException("Luz igual a null");
        }
        
        luces.add(luz);    
    }
    
    /**
     * Elimina de la habitacion la luz especificada
     * 
     * @param n el numero de la luz a eliminar
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     */
    public void eliminaLuz(int n){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
        luces.remove(n-1);
    }
    
    /**
     * Devuelve la luz especificada
     * 
     * @param n el numero de la luz
     * @return una Luz con la luz 
     * @throws IllegalStateException si el numero no corresponde a ninguna luz
     */
    public Luz getLuz(int n){
        if(n <= 0 || n > getNumLuces()){
            throw new IllegalStateException("Numero de luz incorrecto");
        }
         
        return luces.get(n-1);
    }
    
    /**
     * Devuelve las luces de la habitacion
     * 
     * @return un ArrayList con las luces
     */
    public ArrayList<Luz> getLuces(){
        return luces;
    }
    
    /**
     * Verifica si la habitacion se encuentra en modo ALL ON
     * 
     * @return true si lo esta; false en caso contrario 
     */
    public boolean modoAllOn(){
        Luz luz;
        for(int i = 1; i <= getNumLuces(); i++){
            luz = getLuz(i);
            if(luz.apagada() || luz.getIntensidad() != 100 || !luz.getColor().equals(Color.WHITE)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica si la habitacion se encuentra en modo ALL OFF
     * 
     * @return true si lo esta; false en caso contrario 
     */
    public boolean modoAllOff(){
        Luz luz;
        for(int i = 1; i <= getNumLuces(); i++){
            luz = getLuz(i);
            if(luz.encendida()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica si la habitacion se encuentra en modo LECTURA
     * 
     * @return true si lo esta; false en caso contrario 
     */
    public boolean modoLectura(){
        Luz luz1 = getLuz(1);
        Luz luz3 = getLuz(3);
        //Comprobamos que las luces 1 y 3 tienen las caracteristicas del modo
        if(luz1.apagada() || luz3.apagada() || !luz1.getColor().equals(Color.WHITE) || !luz3.getColor().equals(Color.WHITE) || luz1.getIntensidad() != 60 || luz3.getIntensidad() != 60){
            return false;
        }
        //Comprobamos que el resto de luces estan apagadas
        for(int i = 1; i <= getNumLuces(); i++){
            if(i != 1 && i != 3 && getLuz(i).encendida()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifica si la habitacion se encuentra en modo AMBIENTE
     * 
     * @return true si lo esta; false en caso contrario 
     */
    public boolean modoAmbiente(){
        Luz luz;
        for(int i = 1; i <= getNumLuces(); i++){
            luz = getLuz(i);
            if(luz.apagada() || !luz.getColor().equals(Color.YELLOW) || luz.getIntensidad() != 30){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Devuelve el modo de iluminacion presente en la habitacion
     * 
     * @return un String con el nombre del modo o "Ninguno" si no hay ninguno
     */
    public String getModoIluminacion(){
        if(modoAllOff()){
            return "ALL OFF";
        }
        if(modoAllOn()){
            return "ALL ON";
        }
        if(modoAmbiente()){
            return "Ambiente";
        }
        if(modoLectura()){
            return "Lectura";
        }
        return "Ninguno";
    }
    
    /**
     * Informa sobre si this es igual al objeto dado
     * 
     * @param obj el objeto especificado
     * @return true si lo son; false en caso contrario
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Habitacion)){
            return false;
        }
        Habitacion hab = (Habitacion) obj;
        return getLuces().equals(hab.getLuces());
    }
    
    /**
     * Devuelve una representacion de la habitacion
     * 
     * @return un String con la representacion
     */
    @Override
    public String toString(){
        String cadena = "";
        for(int i = 0; i < getNumLuces(); i++){
            cadena += "Luz " + i + ": " + getLuz(i) + "\n";
        }
        return cadena;
    }
}
