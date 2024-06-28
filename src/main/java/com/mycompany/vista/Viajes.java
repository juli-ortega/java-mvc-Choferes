package vista;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.mycompany.modelo.Colectivo;
import controlador.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Socio;

public class Viajes extends javax.swing.JFrame {
    
    private Socio socio;
    private JComboBox<String> colectivoComboBox;
    private JTextField pasajerosTextField;
    private JTextField kilometrosTextField;

    public Viajes(Socio socio) {
        this.socio = socio;
        initComponents();
        mostrarDetalleViaje();
    }

    public Viajes() {
    }
    
    private void mostrarDetalleViaje() {
        Controlador controlador = new Controlador();
        for (Colectivo c : controlador.obtenerColectivosDisponibles()) {
            colectivoComboBox.addItem(c.getModelo() + " - " + c.getPatente());
        }
        
        colectivoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = colectivoComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Colectivo selectedColectivo = controlador.obtenerColectivosDisponibles().get(selectedIndex);
                    Socio chofer = selectedColectivo.getChofer();
                    actualizarSocio(chofer);
                }
            }
        });
    }
    
    private void actualizarSocio(Socio chofer) {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalles del viaje:\n");
        sb.append("Socio: ").append(chofer.getNombre()).append("\n");
        jLabel1.setText(sb.toString());
    }
    
    private void aceptarViajeActionPerformed(java.awt.event.ActionEvent evt) {
        Colectivo colectivo = new Colectivo();
        colectivo.aceptarViaje(colectivoComboBox, pasajerosTextField, kilometrosTextField);
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        colectivoComboBox = new JComboBox<>();
        aceptarViaje = new javax.swing.JButton();
        pasajerosTextField = new javax.swing.JTextField(10);
        kilometrosTextField = new javax.swing.JTextField(10);
        JLabel pasajerosLabel = new JLabel("Pasajeros:");
        JLabel kilometrosLabel = new JLabel("Kilómetros:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Cambio aquí

        jLabel1.setText("Detalles del Viaje");

        aceptarViaje.setText("Aceptar Viaje");
        aceptarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarViajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aceptarViaje)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pasajerosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pasajerosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kilometrosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kilometrosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(colectivoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(colectivoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasajerosLabel)
                    .addComponent(pasajerosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kilometrosLabel)
                    .addComponent(kilometrosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarViaje)
                .addGap(25, 25, 25))
        );
        pack();
    }

    private javax.swing.JButton aceptarViaje;
    private javax.swing.JLabel jLabel1;
}

