package paneldomotico.ui;

import paneldomotico.dominio.Habitacion;
import paneldomotico.dominio.Luz;
import java.awt.Color;
import paneldomotico.main.Main;

/**
 *
 * Implementacion del controlador del panel de control de iluminacion
 * 
 * @author aleniet
 * @author davmele
 */

public class ControladorIluminacion {
    private IluminacionUI panelIluminacion;
    private Habitacion habitacion;
    private final String RUTA_IMAGEN_APAGADA = "src/paneldomotico/ui/imagenes/bombillaApagada.png";

    /**
     * Inicializador
     * 
     * @param panelIluminacion la vista del panel de iluminacion
     * @param habitacion el modelo del conjunto de luces
     * @throws NullPointerException si algun argumento es igual a null
     */
    public ControladorIluminacion(IluminacionUI panelIluminacion, Habitacion habitacion){
        if(panelIluminacion == null || habitacion == null){
            throw new NullPointerException("Argumento igual a null");
        }
        this.panelIluminacion = panelIluminacion;
        this.habitacion = habitacion;
    }
    
    /**
     * Actualiza el panel de informacion con los datos de la luz seleccionada
     */
    public void seleccionarLuz(){  
        int numLuzAnterior = panelIluminacion.getNumLuzAnterior();
        panelIluminacion.seleccionarLuz(numLuzAnterior, false);
        int numLuzSeleccionada = panelIluminacion.getNumLuzSeleccionada();
        
        //Si se pulsa sobre una luz seleccionada se evita su deseleccion
        if(numLuzSeleccionada == -1){
            panelIluminacion.seleccionarLuz(numLuzAnterior, true);
            return;
        }
        
        panelIluminacion.setTituloCaracteristicas("Luz " + numLuzSeleccionada);
        Luz luzSeleccionada = habitacion.getLuz(numLuzSeleccionada);
        panelIluminacion.setIntensidad(luzSeleccionada.getIntensidad());
        panelIluminacion.setInfoColor(luzSeleccionada.getColor());
        if(luzSeleccionada.encendida()){
            panelIluminacion.setOnOff("#33bf4f","ON");
        }else{
            panelIluminacion.setOnOff("#ff4447","OFF");
        }
        panelIluminacion.muestraError("");
    }
    
    /**
     * Enciende o apagada la luz seleccionada
     */
    public void onOffAccionado(){
        int numLuzSeleccionada = panelIluminacion.getNumLuzSeleccionada();
        if(numLuzSeleccionada < 0 || numLuzSeleccionada > habitacion.getNumLuces()){
            throw new IllegalStateException("Numero de luz fuera de rango");
        }
        if(habitacion.getLuz(numLuzSeleccionada).apagada()){
            habitacion.encender(numLuzSeleccionada);
            panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, getRutaImagen(habitacion.getLuz(numLuzSeleccionada).getColor()));
            panelIluminacion.setOnOff("#33bf4f","ON");
        } else{
            habitacion.apagar(numLuzSeleccionada);
            panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, RUTA_IMAGEN_APAGADA);
            panelIluminacion.setOnOff("#ff4447","OFF");
        }
        panelIluminacion.muestraModo();
        panelIluminacion.muestraError("");
    }
    
    /**
     * Devuelve la ruta asociada a la imagen de bombilla del color especificado
     * 
     * @param color el color
     * @return un String con la ruta o null si no existe ninguna imagen para el color especificado
     * @throws NullPointerException si el color es igual a null
     */
    public String getRutaImagen(Color color){
        if(color == null){
            throw new NullPointerException("Color igual a null");
        }
        if(color.equals(Color.WHITE)){
            return "src/paneldomotico/ui/imagenes/bombillaBlanca.png";
        }
        if(color.equals(Color.BLUE)){
            return "src/paneldomotico/ui/imagenes/bombillaAzul.png";
        }
        if(color.equals(Color.YELLOW)){
            return "src/paneldomotico/ui/imagenes/bombillaAmarillo.png";
        }
        if(color.equals(Color.RED)){
            return "src/paneldomotico/ui/imagenes/bombillaRoja.png";
        }
        if(color.equals(Color.GREEN)){
            return "src/paneldomotico/ui/imagenes/bombillaVerde.png";
        }
        return null;
    }
    
    /**
     * Enciende todas las luces
     */
    public void modoAllOnAccionado(){
        habitacion.encenderTodas();
        habitacion.setColor(Color.WHITE);
        habitacion.setIntensidad(100);
        panelIluminacion.setIntensidad(100);
        panelIluminacion.setInfoColor(Color.WHITE);
        panelIluminacion.encenderTodas(getRutaImagen(Color.WHITE));
        panelIluminacion.desiluminaModos();
        panelIluminacion.iluminaModoAllOn();
        panelIluminacion.setOnOff("#33bf4f","ON");
        panelIluminacion.muestraError("");
    }
    
    /**
     * Apaga todas las luces
     */
    public void modoAllOffAccionado(){
        habitacion.apagarTodas();
        panelIluminacion.apagarTodas(RUTA_IMAGEN_APAGADA);
        panelIluminacion.desiluminaModos();
        panelIluminacion.iluminaModoAllOff();
        panelIluminacion.setOnOff("#ff4447","OFF");
        panelIluminacion.muestraError("");
    }
    
    /**
     * Establece las caracteristicas del modo ambiente
     */
    public void modoAmbienteAccionado(){
        habitacion.encenderTodas();
        habitacion.setIntensidad(30);
        habitacion.setColor(Color.YELLOW);
        panelIluminacion.encenderTodas(getRutaImagen(Color.YELLOW));
        panelIluminacion.setIntensidad(30);
        panelIluminacion.setInfoColor(Color.YELLOW);
        panelIluminacion.desiluminaModos();
        panelIluminacion.iluminaModoAmbiente();
        panelIluminacion.setOnOff("#33bf4f","ON");
        panelIluminacion.muestraError("");
    }
    
    /**
     * Establece las caracteristicas del modo lectura
     */
    public void modoLecturaAccionado(){
        habitacion.apagarTodas();
        habitacion.encender(1);
        habitacion.encender(3);
        habitacion.setIntensidad(1, 60);
        habitacion.setIntensidad(3, 60);
        habitacion.setColor(1, Color.WHITE);
        habitacion.setColor(3, Color.WHITE);
        
        panelIluminacion.apagarTodas(RUTA_IMAGEN_APAGADA);
        panelIluminacion.cambiarImagenLuz(1, getRutaImagen(Color.WHITE));
        panelIluminacion.cambiarImagenLuz(3, getRutaImagen(Color.WHITE));
        panelIluminacion.setIntensidad(habitacion.getLuz(panelIluminacion.getNumLuzSeleccionada()).getIntensidad());
        panelIluminacion.setInfoColor(habitacion.getLuz(panelIluminacion.getNumLuzSeleccionada()).getColor());
        panelIluminacion.desiluminaModos();
        panelIluminacion.iluminaModoLectura();
        if(panelIluminacion.getNumLuzSeleccionada() == 1 || panelIluminacion.getNumLuzSeleccionada() == 3){
            panelIluminacion.setOnOff("#33bf4f","ON");
        }else{
            panelIluminacion.setOnOff("#ff4447","OFF");
        }
        panelIluminacion.muestraError("");
    }
    
    /**
     * Modifica la intensidad de la luz seleccionada si esta encendida.
     * Si esta apagada no se hacen cambios
     */
    public void intensidadModificada(){
        modificarIntensidad(panelIluminacion.getIntensidad());
    }
    
    public void modificarIntensidad(int intensidad){
        int numLuzModificada = panelIluminacion.getNumLuzSeleccionada();
        if(habitacion.getLuz(numLuzModificada).encendida()){
            panelIluminacion.muestraError("");
            habitacion.setIntensidad(numLuzModificada, intensidad);
            panelIluminacion.setIntensidad(intensidad);
            panelIluminacion.muestraModo();
        }else{
            panelIluminacion.muestraError("No se puede modificar la intensidad de una luz apagada");
            panelIluminacion.setIntensidad(habitacion.getLuz(numLuzModificada).getIntensidad());
        }
    }
    
    /**
     * Modifica la intensidad de la luz seleccionada si esta encendida.
     * Si esta apagada no se hacen cambios
     */
    public void textoIntensidadModificada(){
        modificarIntensidad(Integer.parseInt(panelIluminacion.getIntensidadTexto()));           
    }
    
    /**
     * Modifica el color si la luz esta encendida.
     * Si esta apagada no se hacen cambios
     */
    public void colorModificado(){
        int numLuzSeleccionada = panelIluminacion.getNumLuzSeleccionada();
        Luz luzSeleccionada = habitacion.getLuz(numLuzSeleccionada);
        if(luzSeleccionada.apagada()){
            panelIluminacion.muestraError("No se puede modificar el color de una luz apagada");
            panelIluminacion.setInfoColor(luzSeleccionada.getColor());
            return;
        }
        
        String color = panelIluminacion.getColorSeleccionado();
        switch(color){
            case "Blanco":
                luzSeleccionada.setColor(Color.WHITE);
                panelIluminacion.setInfoColor(Color.WHITE);
                panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, getRutaImagen(Color.WHITE));
                break;
            case "Azul":
                luzSeleccionada.setColor(Color.BLUE);
                panelIluminacion.setInfoColor(Color.BLUE);
                panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, getRutaImagen(Color.BLUE));
                break;
            case "Rojo":
                luzSeleccionada.setColor(Color.RED);
                panelIluminacion.setInfoColor(Color.RED);
                panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, getRutaImagen(Color.RED));
                break;
            case "Amarillo":
                luzSeleccionada.setColor(Color.YELLOW);
                panelIluminacion.setInfoColor(Color.YELLOW);
                panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, getRutaImagen(Color.YELLOW));
                break;
            case "Verde":
                luzSeleccionada.setColor(Color.GREEN);
                panelIluminacion.setInfoColor(Color.decode("#33bf4f"));
                panelIluminacion.cambiarImagenLuz(numLuzSeleccionada, getRutaImagen(Color.GREEN));
                break;
            default:
        }
        panelIluminacion.muestraError("");
        panelIluminacion.muestraModo();
    }
    
    /**
     * Vuelve a la vista del panel de control principal
     */
    public void volver(){
        Main.getPanelControlStateMachine().setVistaPanelPrincipal();
    }
}