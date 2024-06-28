package com.mycompany.vista;

import modelo.Socio;
import controlador.Controlador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;

public class CrearSocio extends JFrame {

    private JLabel nombreLabel;
    private JLabel legajoLabel;
    private JLabel reemplazarLabel;
    private JTextField nombreField;
    private JTextField legajoField;
    private JComboBox<String> reemplazarComboBox;
    private JButton guardarBtn;
    private JButton cancelarBtn;

    public CrearSocio() {
        initComponents();
    }

    private void initComponents() {
        nombreLabel = new JLabel("Nombre:");
        legajoLabel = new JLabel("Legajo:");
        reemplazarLabel = new JLabel("Reemplazar Socio:");
        nombreField = new JTextField();
        legajoField = new JTextField();
        reemplazarComboBox = new JComboBox<>();
        guardarBtn = new JButton("Guardar");
        cancelarBtn = new JButton("Cancelar");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Crear Socio");

        // Ejemplo de datos para el JComboBox
        Controlador controlador = new Controlador();
        for (Socio s : controlador.obtenerSocios()) {
            reemplazarComboBox.addItem(s.getNombre() + " - " + s.getLejago());
        }

        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    guardarSocio(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CrearSocio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        legajoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();  // consume the event to prevent non-numeric input
                }
            }
        });
        
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearSocio.this.dispose();
            }
        });


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(guardarBtn)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(nombreLabel)
                            .addComponent(legajoLabel)
                            .addComponent(reemplazarLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombreField)
                            .addComponent(legajoField)
                            .addComponent(reemplazarComboBox, 0, 200, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelarBtn)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nombreLabel)
                        .addComponent(nombreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(legajoLabel)
                        .addComponent(legajoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(reemplazarLabel)
                        .addComponent(reemplazarComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(guardarBtn)
                        .addComponent(cancelarBtn))
                    .addContainerGap(30, Short.MAX_VALUE)
                )
        );
        pack();
    }

    private void guardarSocio(java.awt.event.ActionEvent evt) throws Exception {
        Socio socio = new Socio();
        socio.setNombre(nombreField.getText());
        socio.setLejago(Integer.parseInt(legajoField.getText()));
        socio.cambiarSocio(reemplazarComboBox, socio);
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getLegajoField() {
        return legajoField;
    }

    public JComboBox<String> getReemplazarComboBox() {
        return reemplazarComboBox;
    }

    public JButton getGuardarBtn() {
        return guardarBtn;
    }

    public JButton getCancelarBtn() {
        return cancelarBtn;
    }
}
