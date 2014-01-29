/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.PypAdmAdmision;
import Entidades.PypAdmResp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author IdlhDeveloper
 */
public class PypAdmRespJpaController implements Serializable {

    public PypAdmRespJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PyP_PersisPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PypAdmResp pypAdmResp) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmAdmision idAdmpyp = pypAdmResp.getIdAdmpyp();
            if (idAdmpyp != null) {
                idAdmpyp = em.getReference(idAdmpyp.getClass(), idAdmpyp.getId());
                pypAdmResp.setIdAdmpyp(idAdmpyp);
            }
            em.persist(pypAdmResp);
            if (idAdmpyp != null) {
                idAdmpyp.getPypAdmRespList().add(pypAdmResp);
                idAdmpyp = em.merge(idAdmpyp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PypAdmResp pypAdmResp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PypAdmResp persistentPypAdmResp = em.find(PypAdmResp.class, pypAdmResp.getId());
            PypAdmAdmision idAdmpypOld = persistentPypAdmResp.getIdAdmpyp();
            PypAdmAdmision idAdmpypNew = pypAdmResp.getIdAdmpyp();
            if (idAdmpypNew != null) {
                idAdmpypNew = em.getReference(idAdmpypNew.getClass(), idAdmpypNew.getId());
                pypAdmResp.setIdAdmpyp(idAdmpypNew);
            }
            pypAdmResp = em.merge(pypAdmResp);
            if (idAdmpypOld != null && !idAdmpypOld.equals(idAdmpypNew)) {
                idAdmpypOld.getPypAdmRespList().remove(pypAdmResp);
                idAdmpypOld = em.merge(idAdmpypOld);
            }
            if (idAdmpypNew != null && !idAdmpypNew.equals(idAdmpypOld)) {
                idAdmpypNew.getPypAdmRespList().add(pypAdmResp);
                idAdmpypNew = em.merge(idAdmpypNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pypAdmResp.getId();
                if (findPypAdmResp(id) == null) {
                    throw new NonexistentEntityException("The pypAdmResp with id " + id + " no longer exists.");
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
            PypAdmResp pypAdmResp;
            try {
                pypAdmResp = em.getReference(PypAdmResp.class, id);
                pypAdmResp.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pypAdmResp with id " + id + " no longer exists.", enfe);
            }
            PypAdmAdmision idAdmpyp = pypAdmResp.getIdAdmpyp();
            if (idAdmpyp != null) {
                idAdmpyp.getPypAdmRespList().remove(pypAdmResp);
                idAdmpyp = em.merge(idAdmpyp);
            }
            em.remove(pypAdmResp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PypAdmResp> findPypAdmRespEntities() {
        return findPypAdmRespEntities(true, -1, -1);
    }

    public List<PypAdmResp> findPypAdmRespEntities(int maxResults, int firstResult) {
        return findPypAdmRespEntities(false, maxResults, firstResult);
    }

    private List<PypAdmResp> findPypAdmRespEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PypAdmResp.class));
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

    public PypAdmResp findPypAdmResp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PypAdmResp.class, id);
        } finally {
            em.close();
        }
    }

    public int getPypAdmRespCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PypAdmResp> rt = cq.from(PypAdmResp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
