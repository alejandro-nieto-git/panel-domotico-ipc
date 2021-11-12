package paneldomotico.main;

import paneldomotico.ui.PanelControlUI;

/**
 * Lanzador de un panel domotico
 * 
 * @author aleniet
 * @author davmele
 */
public class Main {
    private static PanelControlStateMachine panelControlStateMachine;
    
    public static void main(String args[]) {
        panelControlStateMachine = new PanelControlStateMachine();
    }
    
    /**
     * Devuelve la maquina de estados del panel de control principal
     * 
     * @return un PanelControlStateMachine con la maquina de estados
     */
    public static PanelControlStateMachine getPanelControlStateMachine(){
        return panelControlStateMachine;
    }
}
