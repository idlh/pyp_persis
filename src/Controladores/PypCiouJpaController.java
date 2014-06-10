/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.PypCiou;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author IdlhDeveloper
 */
public class PypCiouJpaController implements Serializable {

    public PypCiouJpaController() {
       this.emf = Persistence.createEntityManagerFactory("PyP_PersisPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PypCiou pypCiou) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pypCiou);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PypCiou pypCiou) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pypCiou = em.merge(pypCiou);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pypCiou.getId();
                if (findPypCiou(id) == null) {
                    throw new NonexistentEntityException("The pypCiou with id " + id + " no longer exists.");
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
            PypCiou pypCiou;
            try {
                pypCiou = em.getReference(PypCiou.class, id);
                pypCiou.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pypCiou with id " + id + " no longer exists.", enfe);
            }
            em.remove(pypCiou);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PypCiou> findPypCiouEntities() {
        return findPypCiouEntities(true, -1, -1);
    }

    public List<PypCiou> findPypCiouEntities(int maxResults, int firstResult) {
        return findPypCiouEntities(false, maxResults, firstResult);
    }

    private List<PypCiou> findPypCiouEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PypCiou.class));
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

    public PypCiou findPypCiou(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PypCiou.class, id);
        } finally {
            em.close();
        }
    }

    public int getPypCiouCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PypCiou> rt = cq.from(PypCiou.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<PypCiou>find_CIOU(){
        Query Q=null;
        EntityManager em=getEntityManager();
        Q=em.createQuery("SELECT c FROM PypCiou c WHERE c.estado='1'");
        Q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return Q.getResultList();
    }
}
