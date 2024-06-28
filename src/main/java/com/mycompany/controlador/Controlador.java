package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Socio;
import com.mycompany.modelo.Colectivo;
import com.mycompany.persistencia.ColectivoJpaController;
import com.mycompany.persistencia.SocioJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Menu;
import com.mycompany.vista.CrearColectivo;
import com.mycompany.vista.CrearSocio;
import vista.Viajes;

public class Controlador {
    
    private Menu menu;

    public Controlador() {
    }
    
    public Controlador(Menu menu) {
        this.menu = menu;
        initView();
        initController();
    }

    private void initView() {
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }

    private void initController() {
        menu.getRealizarViajeBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarViaje();
            }
        });

        menu.getRealizarIntercambioBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socio socioSeleccionado = obtenerSocioSeleccionado();
                    socioSeleccionado.realizar_intercambio(obtenerSocios(), obtenerColectivosDisponibles());
                } catch (Exception ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        menu.getMayorKmBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Colectivo colectivo = new Colectivo();
                colectivo.mayor_kilometraje(obtenerColectivosDisponibles());
            }
        });
        menu.getCrearSocioBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearSocio();
            }
        });
        
        menu.getCrearColectivoBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearColectivo();
            }
        });

        menu.getExitBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void realizarViaje() {
        Socio socioSeleccionado = obtenerSocioSeleccionado();
        List<Colectivo> colectivosDisponibles = obtenerColectivosDisponibles();
        if (socioSeleccionado != null && !colectivosDisponibles.isEmpty()) {
            Viajes viaje = new Viajes(socioSeleccionado);
            viaje.setVisible(true);
            viaje.setLocationRelativeTo(menu);
        } else {
            JOptionPane.showMessageDialog(menu, "No hay socio seleccionado o no hay colectivos disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void CrearColectivo(){
        CrearColectivo crearColectivo = new CrearColectivo();
        crearColectivo.setVisible(true);
        crearColectivo.setLocationRelativeTo(null);
    }
    private void CrearSocio(){
        CrearSocio crearSocio = new CrearSocio();
        crearSocio.setVisible(true);
        crearSocio.setLocationRelativeTo(null);
    }

    private void exit() {
        System.exit(0);
    }

    private Socio obtenerSocioSeleccionado() {
        SocioJpaController sjc = new SocioJpaController();
        List<Socio> socios = sjc.obtenerTodosLosSocios();
        return socios.isEmpty() ? null : socios.get(0);
    }
    
    public List<Socio> obtenerSocios() {
        SocioJpaController sjc = new SocioJpaController();
        List<Socio> socios = sjc.obtenerTodosLosSocios();
        return socios;
    }
    
    public List<Colectivo> obtenerColectivosDisponibles() {
        ColectivoJpaController cjc = new ColectivoJpaController();
        List<Colectivo> colectivos = cjc.obtenerTodosLosColectivos();
        return colectivos;
    }

    public void iniciar() {
        SocioJpaController sjc = new SocioJpaController();
        
        try {
            Socio socio1 = null;
            Socio socio2 = null; 
            
            if (sjc.findSocioById(1) == null && sjc.findSocioById(2) == null) {
                socio1 = new Socio(12345678, "Juan");
                socio2 = new Socio(46507172, "Raul");
                sjc.create(socio2);
                sjc.create(socio1);
            }
            // Ahora podemos continuar con la creación de colectivos
            ColectivoJpaController cjc = new ColectivoJpaController();
            List<Colectivo> colectivosExistentes = cjc.obtenerTodosLosColectivos();
            if (colectivosExistentes.isEmpty()) {
                // Si no existen colectivos, crearlos
                if (socio1 != null && socio2 != null) {
                    Colectivo colectivo1 = new Colectivo(60, "AAB124BC", "Mercedes Benz", 0, socio1);
                    Colectivo colectivo2 = new Colectivo(30, "AAC345DF", "Volvo", 0, socio2);
                    cjc.create(colectivo1);
                    cjc.create(colectivo2);
                } else {
                    System.out.println("No se pueden crear colectivos porque faltan socios.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // Finalmente, inicializar la interfaz de usuario
        Menu menu = new Menu();
        Controlador controlador = new Controlador(menu);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
}

}
