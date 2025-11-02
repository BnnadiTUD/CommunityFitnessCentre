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
	
	/*not ideal
	 * public void removeMember(Member m) { EntityManager em =
	 * emf.createEntityManager(); em.getTransaction().begin();
	 * em.remove(em.merge(m)); em.getTransaction().commit(); em.close(); }
	 */
	
	public void removeMember(long id) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	        Member m = em.find(Member.class, id);
	            em.remove(m);
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
	
	// using for assiggning plans and payments to an existing member
	public Member findById(Long id) {
	    EntityManager em = emf.createEntityManager();
        em.close();
	        return em.find(Member.class, id);	    
	}
	
	public Member assignExistingMemberToCentre(long centreId, long memberId) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	        CommunityCentre centre = em.find(CommunityCentre.class, centreId);
	        Member member = em.find(Member.class, memberId);
	        centre.getMembers().add(member);
	        em.getTransaction().commit();
	        em.close();
	        return member;
	}
	
	public Member addNewMemberToCentre(long centreId, Member m) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	        CommunityCentre centre = em.find(CommunityCentre.class, centreId);
	        centre.getMembers().add(m);
	        em.persist(m);
	        em.getTransaction().commit();
	        em.close();

	        return m;
	}
	
}
