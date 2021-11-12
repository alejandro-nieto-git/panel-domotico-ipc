package paneldomotico.ui;
    
import paneldomotico.dominio.Habitacion;
import paneldomotico.main.Main;

/**
 * Implementacion del controlador de la vista del panel de control de persianas
 * 
 * @author aleniet
 * @author davmele
 */
public class ControladorPersianas {
    private Habitacion habitacion;
    private PersianasUI vistaPersianas;
    
    /**
     * Inicializador
     *
     * @param vistaPersianas la vista del panel de persianas
     * @param habitacion el modelo de la habitacion
     * @throws NullPointerException si la vista es null
     * @throws NullPointerException si el modelo de la habitacion es null
     */
    public ControladorPersianas(PersianasUI vistaPersianas, Habitacion habitacion){
        if(vistaPersianas == null){
            throw new NullPointerException("Vista null");
        }
        if(habitacion == null){
            throw new NullPointerException("Modelo de habitacion null");
        }
        this.habitacion = habitacion;
        this.vistaPersianas = vistaPersianas;
    }
    
    /**
     * Actualiza el nivel de cada persiana al indicado                               
     */
    public void nivelPersianaModificado(){
        int nivel;
        for(int i = 1; i <= habitacion.getNumPersianas(); i++){
            nivel = vistaPersianas.getNivelPersiana(i);
            habitacion.setNivelPersiana(i, nivel);
            vistaPersianas.setTextoNivelPersiana(i, nivel);
            vistaPersianas.setImagenPersiana(i, nivel);
        }
    }
    
    /**
     * Actualiza el nivel de cada persiana al indicado en su texto                                
     */
    public void textoNivelPersianaModificado(){
        
        int nivelPersiana;
        int numPersiana = 1;
        try{
            for(numPersiana = 1; numPersiana <= habitacion.getNumPersianas(); numPersiana++){
                nivelPersiana = Integer.parseInt(vistaPersianas.getTextoNivelPersiana(numPersiana));
                if(nivelPersiana < 0 || nivelPersiana > 100){
                    throw new IllegalArgumentException();
                }
                vistaPersianas.setTextoNivelPersiana(numPersiana, nivelPersiana);
                habitacion.setNivelPersiana(numPersiana, nivelPersiana);
                vistaPersianas.setNivelPersiana(numPersiana, nivelPersiana);
                vistaPersianas.setImagenPersiana(numPersiana, nivelPersiana);
            }
        } catch(Exception e){
            //Si el nivel introducido es incorrecto se reestablece el anterior    
            if(!vistaPersianas.getTextoNivelPersiana(numPersiana).equals("")){
                vistaPersianas.setTextoNivelPersiana(numPersiana,habitacion.getNivelPersiana(numPersiana));
            }
        } 
    } 
        
    
    /**
     * Vuelve a la vista del panel de control principal
     */
    public void volver(){
        Main.getPanelControlStateMachine().setVistaPanelPrincipal();
    }
}