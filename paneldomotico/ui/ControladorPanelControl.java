package paneldomotico.ui;

import paneldomotico.dominio.Habitacion;
import paneldomotico.main.Main;

/**
 * Implementacion del controlador de la vista del panel de control principal
 * 
 * @author aleniet
 * @author davmele
 */
public class ControladorPanelControl {
    private PanelControlUI vistaPanelControl;
    private Habitacion habitacion;
    
    /**
     * Inicializador
     * 
     * @param vistaPanelControl la vista del panel de control principal
     * @param habitacion el modelo de la habitacion
     */
    public ControladorPanelControl(PanelControlUI vistaPanelControl, Habitacion habitacion){
        if(vistaPanelControl == null){
            throw new NullPointerException("Vista igual a null");
        }
        if(habitacion == null){
            throw new NullPointerException("Habitacion igual a null");
        }
        this.vistaPanelControl = vistaPanelControl;
        this.habitacion = habitacion;
        vistaPanelControl.actualizar(habitacion.getTemperatura(), habitacion.getModoIluminacion(), getNivelTemperatura());
    }
    
    /**
     * Devuelve el nivel de temperatura de la habitacion
     * 
     * @return "frio" si la temperatura es menor que 17 grados Celsius
     *         "normal" si la temperatura esta entre 17 y 23 grados Celsius
     *         "calor" si la temperatura es mayor que 23 grados Celsius
     */
    public String getNivelTemperatura(){
        double temperatura = habitacion.getTemperatura();
        if(temperatura < 17){
            return "frio";
        }
        if(temperatura > 23){
            return "calor";
        }
        return "normal";
    }
    
    /**
     * Lanza la vista del panel de control de la iluminacion
     */
    public void configuracionIluminacion(){
        Main.getPanelControlStateMachine().setVistaIluminacion();
    }
    
    /**
     * Lanza la vista del panel de control de la calefaccion
     */
    public void configuracionCalefaccion(){
        Main.getPanelControlStateMachine().setVistaCalefaccion();
    }
    
    /**
     * Lanza la vista del panel de control de las persianas
     */
    public void configuracionPersianas(){
        Main.getPanelControlStateMachine().setVistaPersianas();
    }
}