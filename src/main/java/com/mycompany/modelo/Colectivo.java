package com.mycompany.modelo;

import com.mycompany.persistencia.ColectivoJpaController;
import com.mycompany.vista.CrearColectivo;
import controlador.Controlador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Socio;
import modelo.Viaje;
import vista.Viajes;

@Entity
@Table(name = "colectivos")
public class Colectivo implements Viaje{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private int cantidad_pasajeros;
    private String patente;
    private String modelo;
    
    private Socio chofer;
    private int km;
    
    public Colectivo(int cantidad_pasajeros, String patente, String modelo, int km, Socio socio) {
        this.cantidad_pasajeros = cantidad_pasajeros;
        this.patente = patente;
        this.modelo = modelo;
        this.chofer = socio;
        this.km = km;
    }

    public Colectivo(int id) {
        this.id = id;
    }

    public Colectivo() {
    }
    
    //Metodo para verificar la cantidad de kilometros es correcta
    @Override
    public int cant_km(JTextField kilometrosTextField) {
        try {
            int km = Integer.parseInt(kilometrosTextField.getText());
            if (km < 50 || km > 1500) {
                throw new NumberFormatException();
            }
            return km;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de kilómetros debe estar entre 50 y 1500.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    //Metodo para verificar la cantidad de pasajeros correcta
    @Override
    public int cant_pasajeros(JTextField pasajerosTextField,JComboBox<String> colectivoComboBox ) {
        Controlador controlador = new Controlador();
            int selectedIndex = colectivoComboBox.getSelectedIndex();
            Colectivo seleccionado = controlador.obtenerColectivosDisponibles().get(selectedIndex);
        try {
            int pasajeros = Integer.parseInt(pasajerosTextField.getText());
            
            if (pasajeros < 10 || pasajeros >  seleccionado.getCantidad_pasajeros()) {
                throw new NumberFormatException();
            }
            return pasajeros;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El número de pasajeros debe estar entre 10 y " + seleccionado.getCantidad_pasajeros(), "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    
    //Metodo que devuelve el colectivo con mayor kilometraje recorrido
    public void mayor_kilometraje(List<Colectivo> colectivos) {
        String mensaje;
        if (colectivos.get(0).getKm() > colectivos.get(1).getKm()) {
            mensaje = "El colectivo con más kilómetros es " + colectivos.get(0).getModelo() + " con " + colectivos.get(0).getKm() + " km";
        } else {
            mensaje = "El colectivo con más kilómetros es " + colectivos.get(1).getModelo() + " con " + colectivos.get(1).getKm() + " km";
        }
        JOptionPane.showMessageDialog(null, mensaje, "Mayor Kilometraje", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Metodo para aceptar el viaje
    public void aceptarViaje(JComboBox<String> colectivoComboBox, JTextField pasajerosTextField, JTextField kilometrosTextField){
        Controlador controlador = new Controlador();
        int selectedIndex = colectivoComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            int km = cant_km(kilometrosTextField);
            int pasajeros = cant_pasajeros(pasajerosTextField, colectivoComboBox);
            Colectivo seleccionado = controlador.obtenerColectivosDisponibles().get(selectedIndex);
            if (km > 0 && pasajeros > 0) {
                if (pasajeros > 30 && seleccionado.getId() == 2) {
                    // Mostrar mensaje de que el viaje debe hacerse en el micro Mercedes Benz
                    JOptionPane.showMessageDialog(null, "El viaje debe ser realizado en el micro Mercedes Benz porque tiene más de 30 pasajeros.", "Asignación Especial", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ColectivoJpaController cjc = new ColectivoJpaController();
                    km = km + cjc.obtenerKm(seleccionado.getId());
                    cjc.persistirKm(seleccionado.getId(), km);
                    JOptionPane.showMessageDialog(null, "¡Viaje aceptado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    Viajes viaje = new Viajes();
                    viaje.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un colectivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cambiarColectivo(JComboBox<String> reemplazarComboBox, int capacidad, String patente, String modelo) throws Exception{
        
        if (capacidad > 70 || capacidad < 10) {
            JOptionPane.showMessageDialog(null, "La capacidad debe estar entre 10 y 70 pasajeros.", "Error de capacidad", JOptionPane.ERROR_MESSAGE);
        } else if (patente.length() != 6 && patente.length() != 7) {
            JOptionPane.showMessageDialog(null, "La patente debe tener 6 o 7 caracteres.", "Error de patente", JOptionPane.ERROR_MESSAGE);
        } else {
            ColectivoJpaController cjc = new ColectivoJpaController();
            Controlador controlador = new Controlador();
            CrearColectivo crearColectivo = new CrearColectivo();
            List<Colectivo> colectivos = controlador.obtenerColectivosDisponibles();
            int selectedIndex = reemplazarComboBox.getSelectedIndex();
            cjc.delete(colectivos.get(selectedIndex).getId()); //Lo elimina de la base de datos
            Colectivo colectivo = new Colectivo(capacidad, patente.toUpperCase(), modelo,0,colectivos.get(selectedIndex).getChofer());//Crea el colectivo nuevo
            Colectivo seleccionado = colectivos.get(selectedIndex);
            colectivos.remove(seleccionado);
            colectivos.add(selectedIndex, colectivo);
            cjc.edit(colectivos);
            JOptionPane.showMessageDialog(null, "¡Colectivo Reamplazado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            crearColectivo.dispose();
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCantidad_pasajeros() {
        return cantidad_pasajeros;
    }

    public void setCantidad_pasajeros(int cantidad_pasajeros) {
        this.cantidad_pasajeros = cantidad_pasajeros;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Socio getChofer() {
        return chofer;
    }

    public void setChofer(Socio chofer) {
        this.chofer = chofer;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
    
}