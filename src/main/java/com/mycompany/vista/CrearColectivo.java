package com.mycompany.vista;

import com.mycompany.modelo.Colectivo;
import com.mycompany.persistencia.ColectivoJpaController;
import controlador.Controlador;
import java.awt.event.ActionEvent;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;

public class CrearColectivo extends JFrame {

    private JLabel patenteLabel;
    private JLabel modeloLabel;
    private JLabel choferIdLabel;
    private JLabel reemplazarLabel;
    private JLabel capacidadLabel;
    private JTextField patenteField;
    private JTextField modeloField;
    private JTextField choferIdField;
    private JTextField capacidadField;
    private JComboBox<String> reemplazarComboBox;
    private JButton guardarBtn;
    private JButton cancelarBtn;

    public CrearColectivo() {
        initComponents();
    }

    private void initComponents() {
        patenteLabel = new JLabel("Patente:");
        modeloLabel = new JLabel("Modelo:");
        choferIdLabel = new JLabel("Chofer ID:");
        reemplazarLabel = new JLabel("Reemplazar Colectivo:");
        capacidadLabel = new JLabel("Capacidad:");
        patenteField = new JTextField();
        modeloField = new JTextField();
        capacidadField = new JTextField();
        reemplazarComboBox = new JComboBox<>();
        guardarBtn = new JButton("Guardar");
        cancelarBtn = new JButton("Cancelar");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Crear Colectivo");

        // Ejemplo de datos para el JComboBox
        Controlador controlador = new Controlador();
        for (Colectivo c : controlador.obtenerColectivosDisponibles()) {
            reemplazarComboBox.addItem(c.getModelo() + " - " + c.getPatente());
        }
        
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    cambiarColectivo(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CrearColectivo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearColectivo.this.dispose();
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
                            .addComponent(patenteLabel)
                            .addComponent(modeloLabel)
                            .addComponent(reemplazarLabel)
                            .addComponent(capacidadLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(patenteField)
                            .addComponent(modeloField)
                            .addComponent(capacidadField)
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
                        .addComponent(patenteLabel)
                        .addComponent(patenteField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(modeloLabel)
                        .addComponent(modeloField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(capacidadLabel)
                        .addComponent(capacidadField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

    private void cambiarColectivo(java.awt.event.ActionEvent evt) throws Exception {
        Colectivo colectivo = new Colectivo();
        colectivo.cambiarColectivo(reemplazarComboBox,parseInt(capacidadField.getText()), 
                patenteField.getText(), modeloField.getText());
    }

    public JTextField getPatenteField() {
        return patenteField;
    }

    public JTextField getModeloField() {
        return modeloField;
    }

    public JTextField getChoferIdField() {
        return choferIdField;
    }

    public JTextField getCapacidadField() {
        return capacidadField;
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
