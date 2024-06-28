package com.mycompany.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import modelo.Socio;

public class SocioJpaController {
    
    private EntityManagerFactory emf = null;
    
    public SocioJpaController(){
         this.emf = Persistence.createEntityManagerFactory("com.mycompany_GlobalProgramacion_jar_1.0-SNAPSHOTPU");
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Socio socio) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            // Check if there are already 2 socios in the database
            Query query = em.createQuery("SELECT s FROM Socio s");
            List<Socio> socios = query.getResultList();
            
            for (Socio e : socios) {
                System.out.println(e);
            }
            if (socios.size() > 2) {
                JOptionPane.showMessageDialog(null, "No se pueden agregar más de dos socios.");
                throw new Exception("No se pueden agregar más de dos socios.");
            }

            em.persist(socio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Socio> obtenerTodosLosSocios() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Socio> query = em.createQuery("SELECT s FROM Socio s", Socio.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Socio findSocioById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Socio.class, id);
        } finally {
            em.close();
        }
    }
    
    public void deleteSocioById(int id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Socio socio = em.find(Socio.class, id);
            if (socio != null) {
                em.remove(socio);
            } else {
                throw new Exception("Socio no encontrado");
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void reemplazarSocio(Socio socioNuevo) throws Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();

        // Buscar el socio existente por su ID
        Socio socioExistente = em.find(Socio.class, socioNuevo.getId());
        if (socioExistente == null) {
            throw new Exception("Socio no encontrado con ID: " + socioNuevo.getId());
        }

        // Actualizar los campos del socio existente con los del socio nuevo
        socioExistente.setNombre(socioNuevo.getNombre());
        socioExistente.setLejago(socioNuevo.getLejago());
        // Añadir aquí cualquier otro campo que se desee actualizar

        em.merge(socioExistente); // Actualizar el socio existente en la base de datos

        em.getTransaction().commit();
    } finally {
        if (em != null) {
            em.close();
        }
    }
}

}
