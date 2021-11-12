package paneldomotico.ui;

import paneldomotico.dominio.Habitacion;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

/**
 * Implementacion de la vista del panel de control de iluminacion
 * 
 * @author aleniet
 * @author davmele
 */
public class IluminacionUI extends javax.swing.JFrame {
    private final int NUM_LUCES = 5;
    private final Color COLOR_MODOS = Color.decode("#FFEE59");
    private Habitacion habitacion;
    private ControladorIluminacion controlador;
    
            
    /**
     * Inicializador de la vista del panel de iluminacion
     * 
     * @param habitacion el modelo de la habitacion
     * @throws NullPointerException si el modelo de la habitacion es null
     */
    public IluminacionUI(Habitacion habitacion) {
        if(habitacion == null){
            throw new NullPointerException("Modelo de habitacion igual a null");
        }
        initComponents();
        this.habitacion = habitacion;
        controlador = new ControladorIluminacion(this, habitacion);
        
        /* Deshabilitacion de eventos */
        ChangeListener cl = intensidad.getChangeListeners()[0];
        intensidad.removeChangeListener(cl);
        ActionListener al = color.getActionListeners()[0];
        color.removeActionListener(al);
       
        //Se selecciona la luz 1 por defecto
        luz1.setSelected(true);
        setIntensidad(this.habitacion.getIntensidad(getNumLuzSeleccionada()));
        setInfoColor(this.habitacion.getColor(getNumLuzSeleccionada()));
        boolean estadoLuzSeleccionada = this.habitacion.getLuces().get(getNumLuzSeleccionada()-1).encendida();
        if(!estadoLuzSeleccionada){
            luz1.setIcon(new ImageIcon("src/paneldomotico/ui/imagenes/bombillaApagada.png"));
            setOnOff("#ff4447","OFF");
        }
        setOnOff("#33bf4f","ON");
        
        for(int i = 0; i < this.habitacion.getNumLuces(); i++){
            if(this.habitacion.getLuces().get(i).encendida()){
                cambiarImagenLuz(i+1, getRutaImagen(this.habitacion.getLuz(i+1).getColor()));
            }
        }
        muestraModo();  
        
        /* Rehabilitacion de eventos */
        intensidad.addChangeListener(cl);
        color.addActionListener(al);
    }
    
    /**
     * Devuelve el numero de luz seleccionada
     * 
     * @return un int con el numero
     */
    public int getNumLuzSeleccionada(){
        if(luz1.isSelected()){
            return 1;
        }
        if(luz2.isSelected()){
            return 2;
        }
        if(luz3.isSelected()){
            return 3;
        }
        if(luz4.isSelected()){
            return 4;
        }
        if(luz5.isSelected()){
            return 5;
        }
        return -1;
    }
    
    /**
     * Permite seleccionar o deseleccionar la luz especificada
     * 
     * @param numLuz el numero de la luz
     * @param seleccionar true si se quiere seleccionar o false si se quiere deseleccionar
     * @throws IllegalArgumentException si el numero no corresponde a ninguna luz
     */
    public void seleccionarLuz(int numLuz, boolean seleccionar){
        switch(numLuz){
            case 1:
                luz1.setSelected(seleccionar);
                break;
            case 2:
                luz2.setSelected(seleccionar);
                break;
            case 3:
                luz3.setSelected(seleccionar);
                break;
            case 4:
                luz4.setSelected(seleccionar);
                break;
            case 5:
                luz5.setSelected(seleccionar);
                break;
            default:
                throw new IllegalArgumentException("Numero de luz incorrecto");
        }
    }
    
    /**
     * Devuelve el numero de la luz del texto
     * 
     * @return un int con el numero
     */
    public int getNumLuzAnterior(){
        String texto = tituloLuz.getText();
        return texto.charAt(texto.length()-1) - 48;
    }
    
    /**
     * Modifica el texto de el titulo de las caracteristicas mostradas
     * 
     * @param titulo el nuevo titulo de luz
     * @throws NullPointerException si el titulo dado es igual null
     */
    public void setTituloCaracteristicas(String titulo){
        if(titulo == null){
            throw new NullPointerException("Titulo de caracteristicas igual a null");
        }
        tituloLuz.setText(titulo);
    }
    
    /**
     * Modifica el texto de intensidad y el slider a la intensidad dada
     * 
     * @param intensidad la nueva intensidad
     * @throws IllegalArgumentException si la intensidad no esta en el intervalo [0,100]
     */
    public void setIntensidad(int intensidad){
        if(intensidad < 0 || intensidad > 100){
            throw new IllegalArgumentException("Intensidad fuera de rango");
        }
        textoIntensidad.setText(""+intensidad);
        this.intensidad.setValue(intensidad);
    }
    
    /**
     * Modifica la informacion de color
     * 
     * @param color el color
     */
    public void setInfoColor(Color color){
        fondoColor.setBackground(color);
        switch (color.getRGB()){
            //Color blanco
            case -1:
                this.color.setSelectedItem("Blanco");
                break;
            //Color azul
            case -16776961:
                this.color.setSelectedItem("Azul");
                break;
            //Color rojo
            case -65536:
                this.color.setSelectedItem("Rojo");
                break;
            //Color amarillo
            case -256:
                this.color.setSelectedItem("Amarillo");
                break;
            //Color verde
            case -16711936:
                this.color.setSelectedItem("Verde");
                break;
            default:
        }
    }
    
    /**
     * Muestra el modo de iluminacion en el que se encuentra la habitacion
     */
    public void muestraModo(){
        if(habitacion.modoAllOff()){
            clickAllOff();
        } else if(habitacion.modoAllOn()){
            clickAllOn();
        } else if(habitacion.modoAmbiente()){
            clickLuzAmbiental();
        } else if(habitacion.modoLectura()){
            clickModoLectura();
        } else{
            desiluminaModos();
        }     
    }
    
    /**
     * Hace click src/paneldomotico/ui/imagenes/bombillaBlanca.pngen el modo todas encendidas
     */
    public void clickAllOn(){
       modoAllOn.doClick();
    }
    
    /**
     * Hace click en el modo todas apagadas
     */
    public void clickAllOff(){
       modoAllOff.doClick();
    }
    
    /**
     * Hace click en el modo luz ambiental
     */
    public void clickLuzAmbiental(){
       modoAmbiente.doClick();
    }
    
    /**
     * Hace click en el modo lectura
     */
    public void clickModoLectura(){
       modoLectura.doClick();
    }
    
    /**
     * Enciende la luz especificada
     * 
     * @param numLuz el numero de la luz
     * @param imagen la ruta a la imagen asociada a la luz encendida
     * @throws IllegalArgumentException si el numero de luz esta fuera de rango
     * @throws NullPointerException si la ruta es igual a null
     */
    public void cambiarImagenLuz(int numLuz, String imagen){
        if(numLuz < 1 || numLuz > NUM_LUCES){
            throw new IllegalArgumentException("Numero de luz fuera de rango");
        }
        if(imagen == null){
            throw new NullPointerException("Ruta igual a null");
        }
        JToggleButton[] luces = {luz1, luz2, luz3, luz4, luz5};
        luces[numLuz-1].setIcon(new ImageIcon(imagen));
    }
    
    /**
     * Enciende todas las luces con la misma imagen
     * 
     * @param imagen la ruta a la imagen
     * @throws NullPointerException si la ruta es igual a null
     */
    public void encenderTodas(String imagen){
        if(imagen == null){
            throw new NullPointerException("Ruta igual a null");
        }
        for(int i = 1; i <= NUM_LUCES; i++){
            cambiarImagenLuz(i, imagen);
        }
    }
    
    /**
     * Apaga todas las luces
     * 
     * @param imagen la ruta a la imagen de luz apagada
     * @throws NullPointerException si la ruta es igual a null
     */
    public void apagarTodas(String imagen){
        if(imagen == null){
            throw new NullPointerException("Ruta igual a null");
        }
        for(int i = 1; i <= NUM_LUCES; i++){
            cambiarImagenLuz(i, imagen);
        }
    }
    
    /**
     * Ilumina el modo ALL ON
     */
    public void iluminaModoAllOn(){
        modoAllOn.setBackground(COLOR_MODOS);
    }
    
    /**
     * Ilumina el modo ALL OFF
     */
    public void iluminaModoAllOff(){
        modoAllOff.setBackground(COLOR_MODOS);
    }
    
    /**
     * Ilumina el modo ambiente
     */
    public void iluminaModoAmbiente(){
        modoAmbiente.setBackground(COLOR_MODOS);
    }
    
    /**
     * Ilumina el modo lectura
     */
    public void iluminaModoLectura(){
        modoLectura.setBackground(COLOR_MODOS);
    }
    
    /**
     * Desilumina todos los modos
     */
    public void desiluminaModos(){
        Color colorDefecto = new JButton().getBackground();
        modoAllOn.setBackground(colorDefecto);
        modoAllOff.setBackground(colorDefecto);
        modoAmbiente.setBackground(colorDefecto);
        modoLectura.setBackground(colorDefecto);
    }
    
    /**
     * Modifica el color de fondo y texto de on/off
     * 
     * @param color el color especificado
     * @param onOff texto de on/off
     * @throws NullPointerException si el color o el texto son iguales a null
     */
    public void setOnOff(String color, String onOff){
        if(color == null){
            throw new NullPointerException("Color igual a null");
        }
        if(onOff == null){
            throw new NullPointerException("Texto igual a null");
        }
        this.onOff.setBackground(Color.decode(color));
        this.onOff.setText(onOff);
    }
    
    /**
     * Muestra un mensaje de error con la cadena especificada
     * 
     * @param error el mensaje de error 
     * @throws NullPointerException si la cadena es null
     */
    public void muestraError(String error){
        if(error == null){
            throw new NullPointerException("Mensaje de error igual a null"); 
        }
        mensajeError.setText(error);
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
     * Devuelve el color seleccionado
     * 
     * @return un String con el color seleccionado
     */
    public String getColorSeleccionado(){
        return "" + color.getSelectedItem();
    }
    
    /**
     * Devuelve la intensidad de la luz seleccionada
     * 
     * @return un String con la intensidad
     */
    public String getIntensidadTexto(){
        return textoIntensidad.getText();
    }
    
    /**
     * Devuelve la intensidad de la luz seleccionada
     * 
     * @return un int con la intensidad
     */
    public int getIntensidad(){
        return intensidad.getValue();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modos = new javax.swing.JPanel();
        tituloModos = new javax.swing.JLabel();
        modoAllOn = new javax.swing.JButton();
        modoAllOff = new javax.swing.JButton();
        modoAmbiente = new javax.swing.JButton();
        modoLectura = new javax.swing.JButton();
        caracteristicasLuz = new javax.swing.JPanel();
        intensidad = new javax.swing.JSlider();
        tituloIntensidad = new javax.swing.JLabel();
        textoIntensidad = new javax.swing.JTextField();
        tituloLuz = new javax.swing.JLabel();
        tituloColor = new javax.swing.JLabel();
        color = new javax.swing.JComboBox<>();
        fondoColor = new javax.swing.JTextField();
        onOff = new javax.swing.JButton();
        mensajeError = new javax.swing.JLabel();
        luces = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        luz1 = new javax.swing.JToggleButton();
        luz4 = new javax.swing.JToggleButton();
        luz5 = new javax.swing.JToggleButton();
        luz3 = new javax.swing.JToggleButton();
        luz2 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de control de iluminación");
        setLocation(new java.awt.Point(400, 250));

        modos.setBackground(new java.awt.Color(160, 233, 247));
        modos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tituloModos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tituloModos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloModos.setText("MODOS DE SELECCIÓN");

        modoAllOn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modoAllOn.setText("ALL ON");
        modoAllOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoAllOnActionPerformed(evt);
            }
        });

        modoAllOff.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modoAllOff.setText("ALL OFF");
        modoAllOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoAllOffActionPerformed(evt);
            }
        });

        modoAmbiente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modoAmbiente.setText("AMBIENTE");
        modoAmbiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoAmbienteActionPerformed(evt);
            }
        });

        modoLectura.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modoLectura.setText("LECTURA");
        modoLectura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoLecturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modosLayout = new javax.swing.GroupLayout(modos);
        modos.setLayout(modosLayout);
        modosLayout.setHorizontalGroup(
            modosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(modoAllOn, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(modoAllOff, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(modoAmbiente, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(modoLectura, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloModos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modosLayout.setVerticalGroup(
            modosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloModos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modoAllOn)
                    .addComponent(modoAllOff, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modoAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modoLectura, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {modoAllOff, modoAllOn, modoAmbiente, modoLectura});

        caracteristicasLuz.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Características de la luz", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        intensidad.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        intensidad.setMajorTickSpacing(10);
        intensidad.setPaintLabels(true);
        intensidad.setPaintTicks(true);
        intensidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                intensidadStateChanged(evt);
            }
        });

        tituloIntensidad.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tituloIntensidad.setText("Intensidad");

        textoIntensidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoIntensidad.setText("100");
        textoIntensidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoIntensidadKeyReleased(evt);
            }
        });

        tituloLuz.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        tituloLuz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLuz.setText("Luz 1");

        tituloColor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tituloColor.setText("Color");

        color.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanco", "Amarillo", "Rojo", "Azul", "Verde" }));
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });

        fondoColor.setEditable(false);
        fondoColor.setBackground(new java.awt.Color(255, 255, 255));

        onOff.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        onOff.setText("OFF");
        onOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOffActionPerformed(evt);
            }
        });

        mensajeError.setForeground(new java.awt.Color(255, 51, 0));
        mensajeError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout caracteristicasLuzLayout = new javax.swing.GroupLayout(caracteristicasLuz);
        caracteristicasLuz.setLayout(caracteristicasLuzLayout);
        caracteristicasLuzLayout.setHorizontalGroup(
            caracteristicasLuzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caracteristicasLuzLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(caracteristicasLuzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caracteristicasLuzLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(onOff, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caracteristicasLuzLayout.createSequentialGroup()
                        .addComponent(tituloLuz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(107, 107, 107))))
            .addGroup(caracteristicasLuzLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(tituloIntensidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intensidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoIntensidad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(caracteristicasLuzLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mensajeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(caracteristicasLuzLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(tituloColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(color, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(fondoColor)
                .addGap(91, 91, 91))
        );
        caracteristicasLuzLayout.setVerticalGroup(
            caracteristicasLuzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caracteristicasLuzLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tituloLuz, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(onOff, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGroup(caracteristicasLuzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(caracteristicasLuzLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(caracteristicasLuzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(intensidad, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addGroup(caracteristicasLuzLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(tituloIntensidad)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caracteristicasLuzLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoIntensidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addComponent(mensajeError, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(caracteristicasLuzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloColor)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fondoColor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        intensidad.getAccessibleContext().setAccessibleName("");
        onOff.getAccessibleContext().setAccessibleName("on_off");

        luces.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Selección de luz", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N

        luz1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaApagada.png"))); // NOI18N
        luz1.setAutoscrolls(true);
        luz1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luz1ActionPerformed(evt);
            }
        });

        luz4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaApagada.png"))); // NOI18N
        luz4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luz4ActionPerformed(evt);
            }
        });

        luz5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaApagada.png"))); // NOI18N
        luz5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luz5ActionPerformed(evt);
            }
        });

        luz3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaApagada.png"))); // NOI18N
        luz3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luz3ActionPerformed(evt);
            }
        });

        luz2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaApagada.png"))); // NOI18N
        luz2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luz2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Luz 1");

        jLabel2.setText("Luz 2");

        jLabel3.setText("Luz 3");

        jLabel4.setText("Luz 4");

        jLabel5.setText("Luz 5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(luz1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(luz2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(luz3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel4)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(luz4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(luz5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(luz1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luz2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luz3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(luz4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luz5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout lucesLayout = new javax.swing.GroupLayout(luces);
        luces.setLayout(lucesLayout);
        lucesLayout.setHorizontalGroup(
            lucesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lucesLayout.setVerticalGroup(
            lucesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lucesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(luces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(caracteristicasLuz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(volver, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(modos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(luces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(caracteristicasLuz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modoAllOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoAllOnActionPerformed
        controlador.modoAllOnAccionado();
    }//GEN-LAST:event_modoAllOnActionPerformed

    private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
        controlador.colorModificado();
    }//GEN-LAST:event_colorActionPerformed

    private void luz1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luz1ActionPerformed
        controlador.seleccionarLuz();
    }//GEN-LAST:event_luz1ActionPerformed

    private void luz2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luz2ActionPerformed
        controlador.seleccionarLuz();
    }//GEN-LAST:event_luz2ActionPerformed

    private void luz3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luz3ActionPerformed
        controlador.seleccionarLuz();
    }//GEN-LAST:event_luz3ActionPerformed

    private void luz4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luz4ActionPerformed
        controlador.seleccionarLuz();
    }//GEN-LAST:event_luz4ActionPerformed

    private void luz5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luz5ActionPerformed
        controlador.seleccionarLuz();
    }//GEN-LAST:event_luz5ActionPerformed

    private void modoAllOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoAllOffActionPerformed
        controlador.modoAllOffAccionado();
    }//GEN-LAST:event_modoAllOffActionPerformed

    private void modoAmbienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoAmbienteActionPerformed
        controlador.modoAmbienteAccionado();
    }//GEN-LAST:event_modoAmbienteActionPerformed

    private void modoLecturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoLecturaActionPerformed
        controlador.modoLecturaAccionado();
    }//GEN-LAST:event_modoLecturaActionPerformed

    private void intensidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_intensidadStateChanged
        controlador.intensidadModificada();
    }//GEN-LAST:event_intensidadStateChanged

    private void onOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onOffActionPerformed
        controlador.onOffAccionado();
    }//GEN-LAST:event_onOffActionPerformed

    private void textoIntensidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoIntensidadKeyReleased
        controlador.textoIntensidadModificada(); 
    }//GEN-LAST:event_textoIntensidadKeyReleased

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        controlador.volver();
    }//GEN-LAST:event_volverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel caracteristicasLuz;
    private javax.swing.JComboBox<String> color;
    private javax.swing.JTextField fondoColor;
    private javax.swing.JSlider intensidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel luces;
    private javax.swing.JToggleButton luz1;
    private javax.swing.JToggleButton luz2;
    private javax.swing.JToggleButton luz3;
    private javax.swing.JToggleButton luz4;
    private javax.swing.JToggleButton luz5;
    private javax.swing.JLabel mensajeError;
    private javax.swing.JButton modoAllOff;
    private javax.swing.JButton modoAllOn;
    private javax.swing.JButton modoAmbiente;
    private javax.swing.JButton modoLectura;
    private javax.swing.JPanel modos;
    private javax.swing.JButton onOff;
    private javax.swing.JTextField textoIntensidad;
    private javax.swing.JLabel tituloColor;
    private javax.swing.JLabel tituloIntensidad;
    private javax.swing.JLabel tituloLuz;
    private javax.swing.JLabel tituloModos;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
