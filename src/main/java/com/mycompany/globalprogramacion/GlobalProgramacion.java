package com.mycompany.globalprogramacion;
import com.mycompany.persistencia.ControladoraDePersistencia;
import controlador.Controlador;

public class GlobalProgramacion {

    public static void main(String[] args) {
       ControladoraDePersistencia cdp = new ControladoraDePersistencia();
       Controlador control = new Controlador();
       control.iniciar();
    }
}
