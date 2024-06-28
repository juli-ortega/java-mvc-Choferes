package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame {

    private JButton realizarViajeBtn;
    private JButton realizarIntercambioBtn;
    private JButton mayorKmBtn;
    private JButton exitBtn;
    private JButton crearSocioBtn;
    private JButton crearColectivoBtn;
    private JLabel jLabel1;

    public Menu() {
        initComponents();
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        realizarViajeBtn = new javax.swing.JButton();
        realizarIntercambioBtn = new javax.swing.JButton();
        mayorKmBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        crearSocioBtn = new javax.swing.JButton(); // Nuevo botón
        crearColectivoBtn = new javax.swing.JButton(); // Nuevo botón

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Menu");

        realizarViajeBtn.setText("Realizar Viaje");

        realizarIntercambioBtn.setText("Realizar Intercambio");

        mayorKmBtn.setText("Mayor KM");

        exitBtn.setText("Exit");

        crearSocioBtn.setText("Crear Socio"); // Configuración del texto del nuevo botón
        crearColectivoBtn.setText("Crear Colectivo"); // Configuración del texto del nuevo botón

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(realizarViajeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(realizarIntercambioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mayorKmBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crearSocioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Añadir el nuevo botón al layout
                    .addComponent(crearColectivoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Añadir el nuevo botón al layout
                    .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(realizarViajeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(realizarIntercambioBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mayorKmBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crearSocioBtn) // Añadir el nuevo botón al layout
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crearColectivoBtn) // Añadir el nuevo botón al layout
                .addGap(18, 18, 18)
                .addComponent(exitBtn)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }   

    public JButton getRealizarViajeBtn() {
        return realizarViajeBtn;
    }

    public JButton getRealizarIntercambioBtn() {
        return realizarIntercambioBtn;
    }

    public JButton getMayorKmBtn() {
        return mayorKmBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }

    public JButton getCrearSocioBtn() {
        return crearSocioBtn;
    }

    public JButton getCrearColectivoBtn() {
        return crearColectivoBtn;
    }
}
