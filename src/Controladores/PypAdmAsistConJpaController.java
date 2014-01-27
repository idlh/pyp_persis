/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.PypAdmAgend;
import Entidades.PypAdmAsistCon;
import Entidades.PypAdmControlProfesionales;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author IdlhDeveloper
 */
public class PypAdmAsistConJpaController implements Serializable {

    public PypAdmAsistConJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PyP_PersisPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PypAdmAsistCon pypAdmAsistCon) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmControlProfesionales idControlPro = pypAdmAsistCon.getIdControlPro();
            if (idControlPro != null) {
                idControlPro = em.getReference(idControlPro.getClass(), idControlPro.getId());
                pypAdmAsistCon.setIdControlPro(idControlPro);
            }
            PypAdmAgend idAgend = pypAdmAsistCon.getIdAgend();
            if (idAgend != null) {
                idAgend = em.getReference(idAgend.getClass(), idAgend.getId());
                pypAdmAsistCon.setIdAgend(idAgend);
            }
            em.persist(pypAdmAsistCon);
            if (idControlPro != null) {
                idControlPro.getPypAdmAsistConList().add(pypAdmAsistCon);
                idControlPro = em.merge(idControlPro);
            }
            if (idAgend != null) {
                idAgend.getPypAdmAsistConList().add(pypAdmAsistCon);
                idAgend = em.merge(idAgend);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PypAdmAsistCon pypAdmAsistCon) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmAsistCon persistentPypAdmAsistCon = em.find(PypAdmAsistCon.class, pypAdmAsistCon.getId());
            PypAdmControlProfesionales idControlProOld = persistentPypAdmAsistCon.getIdControlPro();
            PypAdmControlProfesionales idControlProNew = pypAdmAsistCon.getIdControlPro();
            PypAdmAgend idAgendOld = persistentPypAdmAsistCon.getIdAgend();
            PypAdmAgend idAgendNew = pypAdmAsistCon.getIdAgend();
            if (idControlProNew != null) {
                idControlProNew = em.getReference(idControlProNew.getClass(), idControlProNew.getId());
                pypAdmAsistCon.setIdControlPro(idControlProNew);
            }
            if (idAgendNew != null) {
                idAgendNew = em.getReference(idAgendNew.getClass(), idAgendNew.getId());
                pypAdmAsistCon.setIdAgend(idAgendNew);
            }
            pypAdmAsistCon = em.merge(pypAdmAsistCon);
            if (idControlProOld != null && !idControlProOld.equals(idControlProNew)) {
                idControlProOld.getPypAdmAsistConList().remove(pypAdmAsistCon);
                idControlProOld = em.merge(idControlProOld);
            }
            if (idControlProNew != null && !idControlProNew.equals(idControlProOld)) {
                idControlProNew.getPypAdmAsistConList().add(pypAdmAsistCon);
                idControlProNew = em.merge(idControlProNew);
            }
            if (idAgendOld != null && !idAgendOld.equals(idAgendNew)) {
                idAgendOld.getPypAdmAsistConList().remove(pypAdmAsistCon);
                idAgendOld = em.merge(idAgendOld);
            }
            if (idAgendNew != null && !idAgendNew.equals(idAgendOld)) {
                idAgendNew.getPypAdmAsistConList().add(pypAdmAsistCon);
                idAgendNew = em.merge(idAgendNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pypAdmAsistCon.getId();
                if (findPypAdmAsistCon(id) == null) {
                    throw new NonexistentEntityException("The pypAdmAsistCon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmAsistCon pypAdmAsistCon;
            try {
                pypAdmAsistCon = em.getReference(PypAdmAsistCon.class, id);
                pypAdmAsistCon.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pypAdmAsistCon with id " + id + " no longer exists.", enfe);
            }
            PypAdmControlProfesionales idControlPro = pypAdmAsistCon.getIdControlPro();
            if (idControlPro != null) {
                idControlPro.getPypAdmAsistConList().remove(pypAdmAsistCon);
                idControlPro = em.merge(idControlPro);
            }
            PypAdmAgend idAgend = pypAdmAsistCon.getIdAgend();
            if (idAgend != null) {
                idAgend.getPypAdmAsistConList().remove(pypAdmAsistCon);
                idAgend = em.merge(idAgend);
            }
            em.remove(pypAdmAsistCon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PypAdmAsistCon> findPypAdmAsistConEntities() {
        return findPypAdmAsistConEntities(true, -1, -1);
    }

    public List<PypAdmAsistCon> findPypAdmAsistConEntities(int maxResults, int firstResult) {
        return findPypAdmAsistConEntities(false, maxResults, firstResult);
    }

    private List<PypAdmAsistCon> findPypAdmAsistConEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PypAdmAsistCon.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PypAdmAsistCon findPypAdmAsistCon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PypAdmAsistCon.class, id);
        } finally {
            em.close();
        }
    }

    public int getPypAdmAsistConCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PypAdmAsistCon> rt = cq.from(PypAdmAsistCon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
