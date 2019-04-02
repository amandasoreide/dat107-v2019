package no.hvl.dat107.eao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class AnsattEAO {

    private EntityManagerFactory emf;

    public AnsattEAO() {
        emf = Persistence.createEntityManagerFactory("AnsattProsjektPU");
    }
    
    public Ansatt finnAnsattMedId(int id) {

        EntityManager em = emf.createEntityManager();

        Ansatt ansatt = null;
        try {
            ansatt = em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
        return ansatt;
    }

    public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            //TODO Samme som i b)
            
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
        
    }

    public void slettProsjektdeltagelse(Ansatt a, Prosjekt p) {
    	
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Prosjektdeltagelse pd = null; //JPQL-sp�rring
            		
            // Videre som i b)
            
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    private Prosjektdeltagelse finnProsjektdeltagelse(int ansattId, int prosjektId) {
        
        String queryString = "SELECT pd FROM Prosjektdeltagelse pd " 
                + "WHERE pd.ansatt.id = :ansattId AND pd.prosjekt.id = :prosjektId";

        EntityManager em = emf.createEntityManager();

        Prosjektdeltagelse pd = null;
        try {
            TypedQuery<Prosjektdeltagelse> query 
                    = em.createQuery(queryString, Prosjektdeltagelse.class);
            query.setParameter("ansattId", ansattId);
            query.setParameter("prosjektId", prosjektId);
            pd = query.getSingleResult();
            
        } catch (NoResultException e) {
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return pd;
    }
    
}
