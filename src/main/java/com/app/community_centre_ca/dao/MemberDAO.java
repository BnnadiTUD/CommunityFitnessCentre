package com.app.community_centre_ca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.community_centre_ca.model.*;

public class MemberDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("finalCommunityCentrePU"); 	
    

	public void persist(Member m) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removeCommunityCentre(Member m) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(m));
		em.getTransaction().commit();
		em.close();
	}
	
	public Member merge(Member m) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Member updatedMember = em.merge(m);
		em.getTransaction().commit();
		em.close();
		return updatedMember;
	}
	
	
	public List<Member> getAllMembers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Member> members = new ArrayList<Member>();
		members = em.createQuery("from Member").getResultList();
		em.getTransaction().commit();
		em.close();
		return members;
	}
	

}
