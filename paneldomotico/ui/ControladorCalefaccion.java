package paneldomotico.ui;

import paneldomotico.dominio.Habitacion;
import paneldomotico.main.Main;

/**
 * Implementacion del controlador de la vista del panel de calefaccion
 * 
 * @author aleniet
 * @author davmele
 */
public class ControladorCalefaccion {
    private Habitacion habitacion;
    private CalefaccionUI vistaCalefaccion;
    
    /**
     * Inicializador
     * 
     * @param vistaCalefaccion la vista del panel de calefaccion
     * @param habitacion el modelo de la habitacion
     * @throws NullPointerException si algun argumento es igual a null
     */
    public ControladorCalefaccion(CalefaccionUI vistaCalefaccion, Habitacion habitacion){
        if(vistaCalefaccion == null){
            throw new NullPointerException("Vista de panel de calefaccion igual a null");
        }
        if(habitacion == null){
            throw new NullPointerException("Modelo de habitacion igual a null");
        }
        this.habitacion = habitacion;
        this.vistaCalefaccion = vistaCalefaccion;
    }
    
    /**
     * Aumenta la temperatura de la calefaccion en 0.1 grados
     */
    public void aumentarTemperatura(){
        double temperaturaAumentada = Math.round((habitacion.getTemperatura()+0.1)*10)/10.0;
        if(temperaturaAumentada < 30.1){
            habitacion.setTemperatura(temperaturaAumentada);
            vistaCalefaccion.setNivelTemperatura(temperaturaAumentada);
        }
    }
    
    /**
     * Disminuye la temperatura de la calefaccion en 0.1 grados
     */
    public void disminuirTemperatura(){
        double temperaturaDisminuida = Math.round((habitacion.getTemperatura()-0.1)*10)/10.0;
        if(temperaturaDisminuida > 11.9){
            habitacion.setTemperatura(temperaturaDisminuida);
            vistaCalefaccion.setNivelTemperatura(temperaturaDisminuida);
        }
    }
    
    /**
     * Modifica la temperatura de la calefaccion
     */
    public void temperaturaAjustada(){
        habitacion.setTemperatura(vistaCalefaccion.getTemperatura());
        vistaCalefaccion.setNivelTemperatura(vistaCalefaccion.getTemperatura());
    }
    
    /**
     * Vuelve a la vista del panel de control principal
     */
    public void volver(){
        Main.getPanelControlStateMachine().setVistaPanelPrincipal();
    }
}