package paneldomotico.main;

import paneldomotico.dominio.Habitacion;
import paneldomotico.ui.CalefaccionUI;
import paneldomotico.ui.IluminacionUI;
import paneldomotico.ui.PanelControlUI;
import paneldomotico.ui.PersianasUI;

/**
 *
 * Implementacion de la maquina de estados del panel de control
 * 
 * @author aleniet
 * @author davmele
 *
 */
public class PanelControlStateMachine {
    
    private Habitacion habitacion;
    private javax.swing.JFrame currentState;
    
    /**
     * Inicializador con los valores por defecto de un modelo de habitacion 
     * con 5 bombillas, 4 persianas y calefaccion a 20 grados Celsius 
     * y la vista inicial del panel de control principal.
     */
    public PanelControlStateMachine(){
        
        //Se crea el modelo
        habitacion = new Habitacion(5, 4, 20);
        
        //Se crea la vista inicial
        currentState = new javax.swing.JFrame();
        setVistaPanelPrincipal();
    }
    
    /**
     * Cambia a la vista del panel de control principal
     */
    public void setVistaPanelPrincipal(){
        //Se oculta la vista anterior
        currentState.setVisible(false);
        
        //Se crea la vista del panel de control principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new PanelControlUI(habitacion);
                currentState.setVisible(true);
            }
        });
    }
    
    /**
     * Cambia a la vista del panel de control de la iluminacion
     */
    public void setVistaIluminacion(){
        //Se oculta la vista anterior
        currentState.setVisible(false);
        
        //Se crea la vista del panel de control de la iluminacion
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new IluminacionUI(habitacion);
                currentState.setVisible(true);
            }
        });
    }
    
    /**
     * Cambia a la vista del panel de control de la calefaccion
     */
    public void setVistaCalefaccion(){
        //Se oculta la vista anterior
        currentState.setVisible(false);
        
        //Se crea la vista del panel de control de la calefaccion
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new CalefaccionUI(habitacion);
                currentState.setVisible(true);
            }
        });
    }
    
    /**
     * Cambia a la vista del panel de control de las persianas
     */
    public void setVistaPersianas(){
        //Se oculta la vista anterior
        currentState.setVisible(false);
        
        //Se crea la vista del panel de control de las persianas
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new PersianasUI(habitacion);
                currentState.setVisible(true);
            }
        });
    }
}