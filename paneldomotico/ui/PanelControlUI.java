package paneldomotico.ui;

import javax.swing.ImageIcon;
import paneldomotico.dominio.Habitacion;

/**
 * Implementacion de la vista del panel de control principal
 * 
 * @author aleniet
 * @author davmele
 */
public class PanelControlUI extends javax.swing.JFrame {
   
    private final String RUTA_TERMOMETRO_FRIO = "src/paneldomotico/ui/imagenes/termometroFrio.png";
    private final String RUTA_TERMOMETRO_NORMAL = "src/paneldomotico/ui/imagenes/termometroNormal.png";
    private final String RUTA_TERMOMETRO_CALOR = "src/paneldomotico/ui/imagenes/termometroCalor.png";
    private ControladorPanelControl controlador;
    private Habitacion habitacion;
    
    /**
     * Inicializador
     * 
     * @param modelo el habitacion de la habitacion
     */
    public PanelControlUI(Habitacion modelo) {
        initComponents();
        this.habitacion = modelo;
        controlador = new ControladorPanelControl(this, modelo);
    }
    
    /**
     * Modifica la imagen del termometro
     * 
     * @param nivel "frio", "normal" o "calor" segun la imagen deseada
     * @throws NullPointerException si el nivel es igual a null
     * @throws IllegalArgumentException si el nivel no es uno de los especificados
     */
    public void setImagenTermometro(String nivel){
        if(nivel == null){
            throw new NullPointerException("Nivel igual a null");
        }
        if(nivel.equals("frio")){
            termometro.setDisabledIcon(new ImageIcon(RUTA_TERMOMETRO_FRIO));
            return;
        }
        if(nivel.equals("normal")){
            termometro.setDisabledIcon(new ImageIcon(RUTA_TERMOMETRO_NORMAL));
            return;
        }
        if(nivel.equals("calor")){
            termometro.setDisabledIcon(new ImageIcon(RUTA_TERMOMETRO_CALOR));
            return;
        }
        throw new IllegalArgumentException("Nivel incorrecto");
    }
    
    /**
     * Actualiza la vista segun los parametros especificados
     * 
     * @param temperatura la temperatura de la habitacion
     * @param modo el modo de iluminacion de la habitacion
     * @param nivel el nivel de temperatura: "frio", "normal" o "calor" 
     */
    public void actualizar(double temperatura, String modo, String nivel){
        textoTemperatura.setText(temperatura+"℃");
        textoModo.setText(modo);
        setImagenTermometro(nivel);
        textoNivelPersiana1.setText(habitacion.getNivelPersiana(1)+" %");
        textoNivelPersiana2.setText(habitacion.getNivelPersiana(2)+" %");
        textoNivelPersiana3.setText(habitacion.getNivelPersiana(3)+" %");
        textoNivelPersiana4.setText(habitacion.getNivelPersiana(4)+" %");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneral = new javax.swing.JPanel();
        zonaCalefaccion = new javax.swing.JPanel();
        configuracionCalefaccion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        textoCalefaccion = new javax.swing.JLabel();
        temperatura = new javax.swing.JPanel();
        termometro = new javax.swing.JButton();
        textoTemperatura = new javax.swing.JLabel();
        zonaIluminacion = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        textoIluminacion = new javax.swing.JLabel();
        configuracionIluminacion = new javax.swing.JButton();
        iluminacion = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textoModo = new javax.swing.JLabel();
        zonaPersianas = new javax.swing.JPanel();
        configuracionPersianas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        textoIluminacion1 = new javax.swing.JLabel();
        persianas = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textoNivelPersiana1 = new javax.swing.JLabel();
        textoNivelPersiana3 = new javax.swing.JLabel();
        textoNivelPersiana2 = new javax.swing.JLabel();
        textoNivelPersiana4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de control");
        setLocation(new java.awt.Point(250, 250));

        panelGeneral.setLayout(new java.awt.GridLayout(1, 3, 15, 0));

        zonaCalefaccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaCalefaccion.setToolTipText("");
        zonaCalefaccion.setMaximumSize(new java.awt.Dimension(1000, 1000));

        configuracionCalefaccion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        configuracionCalefaccion.setText("Configuración");
        configuracionCalefaccion.setFocusPainted(false);
        configuracionCalefaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionCalefaccionActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textoCalefaccion.setBackground(new java.awt.Color(255, 255, 255));
        textoCalefaccion.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        textoCalefaccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoCalefaccion.setText("Calefacción");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoCalefaccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(textoCalefaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        temperatura.setBackground(new java.awt.Color(255, 255, 255));
        temperatura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        termometro.setBackground(new java.awt.Color(255, 255, 255));
        termometro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/termometroNormal.png"))); // NOI18N
        termometro.setBorderPainted(false);
        termometro.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/termometroNormal.png"))); // NOI18N
        termometro.setEnabled(false);
        termometro.setPreferredSize(new java.awt.Dimension(50, 50));

        textoTemperatura.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        textoTemperatura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoTemperatura.setText("20℃");

        javax.swing.GroupLayout temperaturaLayout = new javax.swing.GroupLayout(temperatura);
        temperatura.setLayout(temperaturaLayout);
        temperaturaLayout.setHorizontalGroup(
            temperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temperaturaLayout.createSequentialGroup()
                .addComponent(termometro, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(textoTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        temperaturaLayout.setVerticalGroup(
            temperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, temperaturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(temperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(termometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout zonaCalefaccionLayout = new javax.swing.GroupLayout(zonaCalefaccion);
        zonaCalefaccion.setLayout(zonaCalefaccionLayout);
        zonaCalefaccionLayout.setHorizontalGroup(
            zonaCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaCalefaccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zonaCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(configuracionCalefaccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(temperatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        zonaCalefaccionLayout.setVerticalGroup(
            zonaCalefaccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaCalefaccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configuracionCalefaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelGeneral.add(zonaCalefaccion);

        zonaIluminacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textoIluminacion.setBackground(new java.awt.Color(255, 255, 255));
        textoIluminacion.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        textoIluminacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoIluminacion.setText("Iluminación");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoIluminacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textoIluminacion, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );

        configuracionIluminacion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        configuracionIluminacion.setText("Configuración");
        configuracionIluminacion.setFocusPainted(false);
        configuracionIluminacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionIluminacionActionPerformed(evt);
            }
        });

        iluminacion.setBackground(new java.awt.Color(255, 255, 255));
        iluminacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaPanel.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/bombillaPanel.png"))); // NOI18N
        jButton1.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Modo activo");

        textoModo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textoModo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoModo.setText("ALL OFF");

        javax.swing.GroupLayout iluminacionLayout = new javax.swing.GroupLayout(iluminacion);
        iluminacion.setLayout(iluminacionLayout);
        iluminacionLayout.setHorizontalGroup(
            iluminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iluminacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(iluminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(iluminacionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(iluminacionLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(textoModo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
        );
        iluminacionLayout.setVerticalGroup(
            iluminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iluminacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(iluminacionLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoModo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout zonaIluminacionLayout = new javax.swing.GroupLayout(zonaIluminacion);
        zonaIluminacion.setLayout(zonaIluminacionLayout);
        zonaIluminacionLayout.setHorizontalGroup(
            zonaIluminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zonaIluminacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zonaIluminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iluminacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(configuracionIluminacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        zonaIluminacionLayout.setVerticalGroup(
            zonaIluminacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaIluminacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iluminacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configuracionIluminacion, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelGeneral.add(zonaIluminacion);

        zonaPersianas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        configuracionPersianas.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        configuracionPersianas.setText("Configuración");
        configuracionPersianas.setFocusPainted(false);
        configuracionPersianas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionPersianasActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textoIluminacion1.setBackground(new java.awt.Color(255, 255, 255));
        textoIluminacion1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        textoIluminacion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoIluminacion1.setText("Persianas");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoIluminacion1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textoIluminacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        persianas.setBackground(new java.awt.Color(255, 255, 255));
        persianas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/paneldomotico/ui/imagenes/persiana.png"))); // NOI18N
        jButton2.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Niveles");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Persiana 2:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Persiana 1:");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Persiana 4:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("Persiana 3:");

        textoNivelPersiana1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoNivelPersiana1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNivelPersiana1.setText("0");

        textoNivelPersiana3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoNivelPersiana3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNivelPersiana3.setText("0");

        textoNivelPersiana2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoNivelPersiana2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNivelPersiana2.setText("0");

        textoNivelPersiana4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        textoNivelPersiana4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNivelPersiana4.setText("0");

        javax.swing.GroupLayout persianasLayout = new javax.swing.GroupLayout(persianas);
        persianas.setLayout(persianasLayout);
        persianasLayout.setHorizontalGroup(
            persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(persianasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(persianasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(persianasLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(textoNivelPersiana4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(persianasLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(textoNivelPersiana3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(persianasLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(textoNivelPersiana2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(persianasLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(textoNivelPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(persianasLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        persianasLayout.setVerticalGroup(
            persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(persianasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(persianasLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(persianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNivelPersiana4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout zonaPersianasLayout = new javax.swing.GroupLayout(zonaPersianas);
        zonaPersianas.setLayout(zonaPersianasLayout);
        zonaPersianasLayout.setHorizontalGroup(
            zonaPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaPersianasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zonaPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(persianas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(configuracionPersianas, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        zonaPersianasLayout.setVerticalGroup(
            zonaPersianasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zonaPersianasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(persianas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configuracionPersianas, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelGeneral.add(zonaPersianas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configuracionCalefaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionCalefaccionActionPerformed
        controlador.configuracionCalefaccion();
    }//GEN-LAST:event_configuracionCalefaccionActionPerformed

    private void configuracionIluminacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionIluminacionActionPerformed
        controlador.configuracionIluminacion();
    }//GEN-LAST:event_configuracionIluminacionActionPerformed

    private void configuracionPersianasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionPersianasActionPerformed
        controlador.configuracionPersianas();
    }//GEN-LAST:event_configuracionPersianasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton configuracionCalefaccion;
    private javax.swing.JButton configuracionIluminacion;
    private javax.swing.JButton configuracionPersianas;
    private javax.swing.JPanel iluminacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel persianas;
    private javax.swing.JPanel temperatura;
    private javax.swing.JButton termometro;
    private javax.swing.JLabel textoCalefaccion;
    private javax.swing.JLabel textoIluminacion;
    private javax.swing.JLabel textoIluminacion1;
    private javax.swing.JLabel textoModo;
    private javax.swing.JLabel textoNivelPersiana1;
    private javax.swing.JLabel textoNivelPersiana2;
    private javax.swing.JLabel textoNivelPersiana3;
    private javax.swing.JLabel textoNivelPersiana4;
    private javax.swing.JLabel textoTemperatura;
    private javax.swing.JPanel zonaCalefaccion;
    private javax.swing.JPanel zonaIluminacion;
    private javax.swing.JPanel zonaPersianas;
    // End of variables declaration//GEN-END:variables
}