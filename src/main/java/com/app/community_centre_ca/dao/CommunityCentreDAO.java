package com.app.community_centre_ca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.community_centre_ca.model.*;

public class CommunityCentreDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("finalCommunityCentrePU"); 	
    

	public void persist(CommunityCentre cc) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(cc);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removeCommunityCentre(CommunityCentre cc) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(cc));
		em.getTransaction().commit();
		em.close();
	}
	
	public CommunityCentre merge(CommunityCentre cc) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		CommunityCentre updatedCommunityCentre = em.merge(cc);
		em.getTransaction().commit();
		em.close();
		return updatedCommunityCentre;
	}
	
	
	public List<CommunityCentre> getAllCommunityCentres() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<CommunityCentre> communityCentres = new ArrayList<CommunityCentre>();
		communityCentres = em.createQuery("from CommunityCentre").getResultList();
		em.getTransaction().commit();
		em.close();
		return communityCentres;
	}
	
	public CommunityCentre findById(Long id) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.find(CommunityCentre.class, id);
	    } finally {
	        em.close();
	    }
	}

	

}
