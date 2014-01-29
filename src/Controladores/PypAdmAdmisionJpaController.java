/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.PypAdmAdmision;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.PypAdmAsistCon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class PypAdmAdmisionJpaController implements Serializable {

    public PypAdmAdmisionJpaController() {
         this.emf = Persistence.createEntityManagerFactory("PyP_PersisPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PypAdmAdmision pypAdmAdmision) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmAsistCon idAsistcon = pypAdmAdmision.getIdAsistcon();
            if (idAsistcon != null) {
                idAsistcon = em.getReference(idAsistcon.getClass(), idAsistcon.getId());
                pypAdmAdmision.setIdAsistcon(idAsistcon);
            }
            em.persist(pypAdmAdmision);
            if (idAsistcon != null) {
                idAsistcon.getPypAdmAdmisionList().add(pypAdmAdmision);
                idAsistcon = em.merge(idAsistcon);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PypAdmAdmision pypAdmAdmision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmAdmision persistentPypAdmAdmision = em.find(PypAdmAdmision.class, pypAdmAdmision.getId());
            PypAdmAsistCon idAsistconOld = persistentPypAdmAdmision.getIdAsistcon();
            PypAdmAsistCon idAsistconNew = pypAdmAdmision.getIdAsistcon();
            if (idAsistconNew != null) {
                idAsistconNew = em.getReference(idAsistconNew.getClass(), idAsistconNew.getId());
                pypAdmAdmision.setIdAsistcon(idAsistconNew);
            }
            pypAdmAdmision = em.merge(pypAdmAdmision);
            if (idAsistconOld != null && !idAsistconOld.equals(idAsistconNew)) {
                idAsistconOld.getPypAdmAdmisionList().remove(pypAdmAdmision);
                idAsistconOld = em.merge(idAsistconOld);
            }
            if (idAsistconNew != null && !idAsistconNew.equals(idAsistconOld)) {
                idAsistconNew.getPypAdmAdmisionList().add(pypAdmAdmision);
                idAsistconNew = em.merge(idAsistconNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pypAdmAdmision.getId();
                if (findPypAdmAdmision(id) == null) {
                    throw new NonexistentEntityException("The pypAdmAdmision with id " + id + " no longer exists.");
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
            PypAdmAdmision pypAdmAdmision;
            try {
                pypAdmAdmision = em.getReference(PypAdmAdmision.class, id);
                pypAdmAdmision.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pypAdmAdmision with id " + id + " no longer exists.", enfe);
            }
            PypAdmAsistCon idAsistcon = pypAdmAdmision.getIdAsistcon();
            if (idAsistcon != null) {
                idAsistcon.getPypAdmAdmisionList().remove(pypAdmAdmision);
                idAsistcon = em.merge(idAsistcon);
            }
            em.remove(pypAdmAdmision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PypAdmAdmision> findPypAdmAdmisionEntities() {
        return findPypAdmAdmisionEntities(true, -1, -1);
    }

    public List<PypAdmAdmision> findPypAdmAdmisionEntities(int maxResults, int firstResult) {
        return findPypAdmAdmisionEntities(false, maxResults, firstResult);
    }

    private List<PypAdmAdmision> findPypAdmAdmisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PypAdmAdmision.class));
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

    public PypAdmAdmision findPypAdmAdmision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PypAdmAdmision.class, id);
        } finally {
            em.close();
        }
    }

    public int getPypAdmAdmisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PypAdmAdmision> rt = cq.from(PypAdmAdmision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
