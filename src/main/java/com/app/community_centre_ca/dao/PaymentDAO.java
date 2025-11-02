package com.app.community_centre_ca.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.app.community_centre_ca.model.*;

public class PaymentDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("finalCommunityCentrePU"); 	
    

	public void persist(Payment p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removePayment(Payment p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(p));
		em.getTransaction().commit();
		em.close();
	}
	
	//unrealistic but will keep in for learning purpose (in real life once you pay for something it is almost impossible to edit
	public Payment merge(Payment p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Payment updatedPayment = em.merge(p);
		em.getTransaction().commit();
		em.close();
		return updatedPayment;
	}
	
	
	public List<Payment> getAllPayments() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Payment> payments = new ArrayList<Payment>();
		payments = em.createQuery("from Payment").getResultList();
		em.getTransaction().commit();
		em.close();
		return payments;
	}
	
	//used in memeber resource to create a payment and add it to a member
	public Payment addNewPaymentToMember(long memberId, Payment p) {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	        Member member = em.find(Member.class, memberId);
	        member.getPayments().add(p);
	        em.persist(p);
	        em.getTransaction().commit();
	        em.close();

	        return p;
	}

	/*
	 * public Payment assignExistingPaymentToMmber(long memberId, long paymentId) {
	 * EntityManager em = emf.createEntityManager(); em.getTransaction().begin();
	 * 
	 * Member member = em.find(Member.class, memberId); Payment payment =
	 * em.find(Payment.class, paymentId); member.getPayments().add(payment);
	 * em.getTransaction().commit(); em.close(); return payment; }
	 */
	
	public Payment findById(Long id) {
	    EntityManager em = emf.createEntityManager();
        em.close();
	        return em.find(Payment.class, id);	    
	}
	
}

