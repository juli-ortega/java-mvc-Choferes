package modelo;

import com.mycompany.modelo.Colectivo;
import com.mycompany.persistencia.ColectivoJpaController;
import com.mycompany.persistencia.SocioJpaController;
import com.mycompany.vista.CrearSocio;
import controlador.Controlador;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

@Entity
@Table(name = "socios")
public class Socio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private int lejago;
    private String nombre;

    public Socio(int lejago, String nombre) {
        this.lejago = lejago;
        this.nombre = nombre;
    }

    public Socio() {}
    
    //Metodo para intercambiar choferes
    public void realizar_intercambio(List<Socio> socios, List<Colectivo> colectivos) throws Exception {
        // Realizar el intercambio de choferes en memoria
        if (colectivos.get(0).getChofer().getId() == socios.get(0).getId()) {
            colectivos.get(0).setChofer(socios.get(1));
            colectivos.get(1).setChofer(socios.get(0));
        } else {
            colectivos.get(0).setChofer(socios.get(0));
            colectivos.get(1).setChofer(socios.get(1));
        }

        // Persistir los cambios en la base de datos
        ColectivoJpaController cjc = new ColectivoJpaController();
        cjc.edit(colectivos);

        // Mostrar mensajes informativos
        JOptionPane.showMessageDialog(null, "¡Intercambio realizado!");
        JOptionPane.showMessageDialog(null, colectivos.get(0).getModelo() + " es manejado por " + colectivos.get(0).getChofer().getNombre());
        JOptionPane.showMessageDialog(null, colectivos.get(1).getModelo() + " es manejado por " + colectivos.get(1).getChofer().getNombre());
    }

    public void cambiarSocio(JComboBox<String> reemplazarComboBox, Socio socio) throws Exception{
        Controlador controlador = new Controlador();
        SocioJpaController sjc = new SocioJpaController();
        CrearSocio crearSocio = new CrearSocio();
        int selectedIndex = reemplazarComboBox.getSelectedIndex();
        List<Socio> socios = controlador.obtenerSocios();
        Socio seleccionado = socios.get(selectedIndex);
        socio.setId(seleccionado.getId());
        sjc.reemplazarSocio(socio);
        JOptionPane.showMessageDialog(null, "¡Socio intercambiado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        crearSocio.dispose();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getLejago() {
        return lejago;
    }

    public void setLejago(int lejago) {
        this.lejago = lejago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
    


