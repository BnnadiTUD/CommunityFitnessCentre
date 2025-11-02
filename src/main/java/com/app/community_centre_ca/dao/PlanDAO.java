package com.app.community_centre_ca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.community_centre_ca.model.*;

public class PlanDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("finalCommunityCentrePU"); 	
    

	public void persist(Plan p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	/*
	 * public void removePlan(Plan p) { EntityManager em =
	 * emf.createEntityManager(); em.getTransaction().begin();
	 * em.remove(em.merge(p)); em.getTransaction().commit(); em.close(); }
	 */
	
	public void removePlan(long id) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	        Plan p = em.find(Plan.class, id);
	            em.remove(p);
	        em.getTransaction().commit();
	        em.close();
	}
	
	public Plan merge(Plan p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Plan updatedPlan = em.merge(p);
		em.getTransaction().commit();
		em.close();
		return updatedPlan;
	}
	
	
	public List<Plan> getAllPlans() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Plan> plans = new ArrayList<Plan>();
		plans = em.createQuery("from Plan").getResultList();
		em.getTransaction().commit();
		em.close();
		return plans;
	}
	
	
	public Plan assignExistingPlanToMember(long memberId, long planId) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	        Member member = em.find(Member.class, memberId);
	        Plan plan = em.find(Plan.class, planId);
	        member.setPlan(plan);
	        em.getTransaction().commit();
	        em.close();
	        return plan;
	}
	
	public Plan addNewPlanToMember(long memberId, Plan p) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	        Member member = em.find(Member.class, memberId);
	        member.setPlan(p);
	        em.persist(p);
	        em.getTransaction().commit();
	        em.close();

	        return p;
	}
	
	public Plan findById(Long id) {
	    EntityManager em = emf.createEntityManager();
        em.close();
	        return em.find(Plan.class, id);	    
	}
	
}