package com.mycompany.persistencia;

import com.mycompany.modelo.Colectivo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ColectivoJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;
    
    public ColectivoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_GlobalProgramacion_jar_1.0-SNAPSHOTPU");
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Colectivo colectivo) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(colectivo);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public int obtenerKm(int id) {
        EntityManager em = getEntityManager();
        try {
            Colectivo colectivo = em.find(Colectivo.class, id);
            return colectivo != null ? colectivo.getKm() : 0;
        } finally {
            em.close();
        }
    }

    public void persistirKm(int id, int km) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Colectivo colectivo = em.find(Colectivo.class, id);
            if (colectivo != null) {
                colectivo.setKm(km);
                em.merge(colectivo);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(List<Colectivo> colectivos) throws Exception {
        EntityTransaction tx = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            for (Colectivo colectivo : colectivos) {
                em.merge(colectivo); // Actualiza cada colectivo modificado
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex; // Relanza la excepción para que sea manejada fuera
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void delete(int id) throws Exception {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Colectivo colectivo = em.find(Colectivo.class, id);
            if (colectivo != null) {
                em.remove(colectivo); // Elimina el colectivo
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex; // Relanza la excepción para que sea manejada fuera
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Colectivo> obtenerTodosLosColectivos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Colectivo> query = em.createQuery("SELECT c FROM Colectivo c", Colectivo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
