package modelo;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public interface Viaje {
    int cant_km(JTextField jTextField);
    int cant_pasajeros(JTextField jTextField, JComboBox<String> jComboBox);
}
