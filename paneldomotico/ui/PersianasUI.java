package paneldomotico.ui;

import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import paneldomotico.dominio.Habitacion;

/**
 * Implementacion de la vista del panel de control de persianas
 * 
 * @author aleniet
 * @author davmele
 */
public class PersianasUI extends javax.swing.JFrame {
    private Habitacion habitacion;
    private ControladorPersianas controlador;
    
    /**
     * Inicializador de la vista del panel de control de persianas 
     * 
     * @param habitacion el modelo de la habitacion
     * @throws NullPointerException si el modelo de la habitacion es null
     */
    public PersianasUI(Habitacion habitacion) {
        if(habitacion == null){
            throw new NullPointerException("Modelo de habitacion igual a null");
        }
        initComponents();
        
        /* Configuracion de etiquetas */
        Hashtable etiquetas = new Hashtable();
        JLabel etiqueta0 = new JLabel("0");
        etiqueta0.setFont(new java.awt.Font("Dialog", 0, 12));
        etiquetas.put(new Integer(0), etiqueta0);
        JLabel etiqueta50 = new JLabel("50");
        etiqueta50.setFont(new java.awt.Font("Dialog", 0, 12));
        etiquetas.put(new Integer(50), etiqueta50);
        JLabel etiqueta100 = new JLabel("100");
        etiqueta100.setFont(new java.awt.Font("Dialog", 0, 12));
        etiquetas.put(new Integer(100), etiqueta100);
        nivelPersiana1.setLabelTable(etiquetas);
        nivelPersiana2.setLabelTable(etiquetas);
        nivelPersiana3.setLabelTable(etiquetas);
        nivelPersiana4.setLabelTable(etiquetas);
        
        this.habitacion = habitacion;
        controlador = new ControladorPersianas(this, this.habitacion);
        
        /* Se actualiza la vista segun el modelo */
        actualizar();
    }
    
    /**
     * Modifica el texto de nivel de la persiana especificada
     * 
     * @param numPersiana el numero de la persiana 
     * @param nivel el nivel de la persiana
     * @throws IllegalArgumentException si el numero de persiana no corresponde a ninguna persiana
     * @throws IllegalArgumentException si el nivel no esta en el rango [0,100]  
     */
    public void setTextoNivelPersiana(int numPersiana, int nivel){
        if(nivel < 0 || nivel > 100){
            throw new IllegalArgumentException("Nivel fuera de rango");
        }
        switch(numPersiana){
            case 1:
                textoNivelPersiana1.setText(""+nivel);
                break;
            case 2:
                textoNivelPersiana2.setText(""+nivel);
                break;
            case 3:
                textoNivelPersiana3.setText(""+nivel);
                break;
            case 4:
                textoNivelPersiana4.setText(""+nivel);
                break;
            default:
                throw new IllegalArgumentException("El numero de persiana no corresponde a ninguna persiana");
        }
    }
    
    /**
     * Modifica el nivel de la persiana especificada
     * 
     * @param numPersiana el numero de la persiana 
     * @param nivel el nivel de la persiana
     * @throws IllegalArgumentException si el numero de persiana no corresponde a ninguna persiana
     * @throws IllegalArgumentException si el nivel no esta en el rango [0,100] 
     */
    public void setNivelPersiana(int numPersiana, int nivel){
         if(nivel < 0 || nivel > 100){
            throw new IllegalArgumentException("Nivel fuera de rango");
        }
        switch(numPersiana){
            case 1:
                nivelPersiana1.setValue(nivel);
                break;
            case 2:
                nivelPersiana2.setValue(nivel);
                break;
            case 3:
                nivelPersiana3.setValue(nivel);
                break;
            case 4:
                nivelPersiana4.setValue(nivel);
                break;
            default:
                throw new IllegalArgumentException("El numero de persiana no corresponde a ninguna persiana");
        }
    }
    
    /**
     * Actualiza la vista segun el nivel actual de las persianas
     */
    public void actualizar(){
        int numPersianas = habitacion.getNumPersianas();
        
        /* Se desactivan los eventos de los sliders */
        JSlider[] sliders = {nivelPersiana1, nivelPersiana2, nivelPersiana3, nivelPersiana4};
        ChangeListener[] listeners = new ChangeListener[numPersianas];
        ChangeListener listener;
        for(int i = 0; i < numPersianas; i++){
            listener = sliders[i].getChangeListeners()[0];
            listeners[i] = listener;
            sliders[i].removeChangeListener(listener);
        }
        
        /* Se actualiza la vista */
        int nivel;
        for(int i = 1; i <= numPersianas; i++){
            nivel = habitacion.getNivelPersiana(i);
            setNivelPersiana(i, nivel);
            setTextoNivelPersiana(i, nivel);
            setImagenPersiana(i, nivel);
        }
        
        /* Se reactivan los eventos de los sliders */
        for(int i = 0; i < numPersianas; i++){
            sliders[i].addChangeListener(listeners[i]);
        }
    }
    
    /**
     * Devuelve el nivel de la persiana especificada
     * 
     * @param numPersiana el numero de la persiana
     * @return un int con el nivel
     * @throws IllegalArgumentException si el numero no corresponde a ninguna persiana
     */
    public int getNivelPersiana(int numPersiana){
        switch(numPersiana){
            case 1:
                return nivelPersiana1.getValue();
            case 2:
                return nivelPersiana2.getValue();
            case 3:
                return nivelPersiana3.getValue();
            case 4:
                return nivelPersiana4.getValue();
            default:
                throw new IllegalArgumentException("Numero de persiana incorrecto");
        }
    }
    
     /**
     * Devuelve el nivel del texto de la persiana especificada
     * 
     * @param numPersiana el numero de la persiana
     * @return un String con el nivel
     * @throws IllegalArgumentException si el numero no corresponde a ninguna persiana
     */
    public String getTextoNivelPersiana(int numPersiana){
        switch(numPersiana){
            case 1:
                return textoNivelPersiana1.getText();
            case 2:
                return textoNivelPersiana2.getText();
            case 3:
                return textoNivelPersiana3.getText();
            case 4:
                return textoNivelPersiana4.getText();
            default:
                throw new IllegalArgumentException("Numero de persiana incorrecto");
        }
    }
    
    
    /**
     * Modifica la imagen de la persiana especificada a la correspondiente al nivel especificado para esta
     * 
     * @param numPersiana el numero de la persiana
     * @param nivel el nivel de la persiana
     * @throws IllegalArgumentException si el numero de la persiana no esta en el rango [1,numPersianas]
     * @throws IllegalArgumentException si el nivel de la persiana no esta en el rango [0,100]
     */
    public void setImagenPersiana(int numPersiana, int nivel){
        if(nivel < 0 || nivel > 100){
            throw new IllegalArgumentException("Nivel fuera de rango");
        }
        if(numPersiana <= 0 || numPersiana > habitacion.getNumPersianas()){
            throw new IllegalArgumentException("Numero de persiana fuera de rango");
        }
        
        JButton[] persianas = {imagenPersiana1, imagenPersiana2, imagenPersiana3, imagenPersiana4};
        ImageIcon imagenPersiana = null;

        if(nivel == 0){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajada.png");
        } else if(nivel <= 12){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando7.png");
        } else if(nivel <= 24){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando6.png");
        } else if(nivel <= 36){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando5.png");
        } else if(nivel <= 48){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando4.png");
        } else if(nivel <= 60){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando3.png");
        } else if(nivel <= 72){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando2.png");
        } else if(nivel <= 84){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaBajando1.png");
        } else if(nivel > 84){
            imagenPersiana = new ImageIcon("src/paneldomotico/ui/imagenes/persianaSubida.png");
        }
                                       
        persianas[numPersiana - 1].setIcon(imagenPersiana);
        persianas[numPersiana - 1].setDisabledIcon(imagenPersiana);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        imagenPersiana2 = new javax.swing.JButton();
        nivelPersiana2 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        textoNivelPersiana2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nivelPersiana1 = new javax.swing.JSlider();
        imagenPersiana1 = new javax.swing.JButton();
        textoNivelPersiana1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        nivelPersiana3 = new javax.swing.JSlider();
        imagenPersiana3 = new javax.swing.JButton();
        textoNivelPersiana3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        imagenPersiana4 = new javax.swing.JButton();
        nivelPersiana4 = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        textoNivelPersiana4 = new javax.swing.JTextField();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de control de persianas");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(400, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Persiana 2", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        imagenPersiana2.setBackground(new java.awt.Color(255, 255, 255));
        imagenPersiana2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana2.setBorderPainted(false);
        imagenPersiana2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana2.setEnabled(false);

        nivelPersiana2.setMinorTickSpacing(10);
        nivelPersiana2.setOrientation(javax.swing.JSlider.VERTICAL);
        nivelPersiana2.setPaintLabels(true);
        nivelPersiana2.setPaintTicks(true);
        nivelPersiana2.setValue(0);
        nivelPersiana2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nivelPersiana2StateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nivel");

        textoNivelPersiana2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoNivelPersiana2.setText("0");
        textoNivelPersiana2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoNivelPersiana2FocusLost(evt);
            }
        });
        textoNivelPersiana2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoNivelPersiana2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nivelPersiana2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(imagenPersiana2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nivelPersiana2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textoNivelPersiana2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(79, 79, 79)))
                    .addComponent(imagenPersiana2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Persiana 1", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nivel");

        nivelPersiana1.setMinorTickSpacing(10);
        nivelPersiana1.setOrientation(javax.swing.JSlider.VERTICAL);
        nivelPersiana1.setPaintLabels(true);
        nivelPersiana1.setPaintTicks(true);
        nivelPersiana1.setValue(0);
        nivelPersiana1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nivelPersiana1StateChanged(evt);
            }
        });

        imagenPersiana1.setBackground(new java.awt.Color(255, 255, 255));
        imagenPersiana1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana1.setBorderPainted(false);
        imagenPersiana1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana1.setEnabled(false);

        textoNivelPersiana1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoNivelPersiana1.setText("0");
        textoNivelPersiana1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoNivelPersiana1FocusLost(evt);
            }
        });
        textoNivelPersiana1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoNivelPersiana1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nivelPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(imagenPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nivelPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagenPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNivelPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Persiana 3", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nivel");

        nivelPersiana3.setMinorTickSpacing(10);
        nivelPersiana3.setOrientation(javax.swing.JSlider.VERTICAL);
        nivelPersiana3.setPaintLabels(true);
        nivelPersiana3.setPaintTicks(true);
        nivelPersiana3.setValue(0);
        nivelPersiana3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nivelPersiana3StateChanged(evt);
            }
        });

        imagenPersiana3.setBackground(new java.awt.Color(255, 255, 255));
        imagenPersiana3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana3.setBorderPainted(false);
        imagenPersiana3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana3.setEnabled(false);

        textoNivelPersiana3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoNivelPersiana3.setText("0");
        textoNivelPersiana3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoNivelPersiana3FocusLost(evt);
            }
        });
        textoNivelPersiana3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoNivelPersiana3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nivelPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(imagenPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nivelPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textoNivelPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(77, 77, 77)))
                    .addComponent(imagenPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Persiana 4", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        imagenPersiana4.setBackground(new java.awt.Color(255, 255, 255));
        imagenPersiana4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana4.setBorderPainted(false);
        imagenPersiana4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        imagenPersiana4.setEnabled(false);

        nivelPersiana4.setMinorTickSpacing(10);
        nivelPersiana4.setOrientation(javax.swing.JSlider.VERTICAL);
        nivelPersiana4.setPaintLabels(true);
        nivelPersiana4.setPaintTicks(true);
        nivelPersiana4.setValue(0);
        nivelPersiana4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nivelPersiana4StateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nivel");

        textoNivelPersiana4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoNivelPersiana4.setText("0");
        textoNivelPersiana4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoNivelPersiana4FocusLost(evt);
            }
        });
        textoNivelPersiana4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoNivelPersiana4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nivelPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(imagenPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nivelPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imagenPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoNivelPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(volver)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(volver, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nivelPersiana2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nivelPersiana2StateChanged
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_nivelPersiana2StateChanged

    private void nivelPersiana1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nivelPersiana1StateChanged
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_nivelPersiana1StateChanged

    private void nivelPersiana3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nivelPersiana3StateChanged
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_nivelPersiana3StateChanged

    private void nivelPersiana4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nivelPersiana4StateChanged
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_nivelPersiana4StateChanged

    private void textoNivelPersiana1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNivelPersiana1KeyReleased
        controlador.textoNivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana1KeyReleased

    private void textoNivelPersiana2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNivelPersiana2KeyReleased
        controlador.textoNivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana2KeyReleased

    private void textoNivelPersiana3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNivelPersiana3KeyReleased
        controlador.textoNivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana3KeyReleased

    private void textoNivelPersiana4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNivelPersiana4KeyReleased
        controlador.textoNivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana4KeyReleased

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        controlador.volver();
    }//GEN-LAST:event_volverActionPerformed

    private void textoNivelPersiana1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoNivelPersiana1FocusLost
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana1FocusLost

    private void textoNivelPersiana2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoNivelPersiana2FocusLost
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana2FocusLost

    private void textoNivelPersiana3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoNivelPersiana3FocusLost
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana3FocusLost

    private void textoNivelPersiana4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoNivelPersiana4FocusLost
        controlador.nivelPersianaModificado();
    }//GEN-LAST:event_textoNivelPersiana4FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton imagenPersiana1;
    private javax.swing.JButton imagenPersiana2;
    private javax.swing.JButton imagenPersiana3;
    private javax.swing.JButton imagenPersiana4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSlider nivelPersiana1;
    private javax.swing.JSlider nivelPersiana2;
    private javax.swing.JSlider nivelPersiana3;
    private javax.swing.JSlider nivelPersiana4;
    private javax.swing.JTextField textoNivelPersiana1;
    private javax.swing.JTextField textoNivelPersiana2;
    private javax.swing.JTextField textoNivelPersiana3;
    private javax.swing.JTextField textoNivelPersiana4;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
