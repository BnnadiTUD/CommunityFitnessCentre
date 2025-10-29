package com.app.community_centre_ca.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@XmlRootElement(name = "member")
@Entity
public class Member {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")                                
    private Long id;
	
	@Column(name = "Member_Name", nullable = true, length = 100)
	private String name;
	
	@Column(name = "Member_Age", nullable = true)
	private int age;
	
	@Column(name = "Member_Address", nullable = true, length = 100)
	private String address;
	
	@Column(name = "Fitness_Goal", nullable = true, length = 100)
	private String goal;
	
	  @ManyToOne(fetch = FetchType.LAZY, optional = true)
	  @JoinColumn(name = "plan_id", nullable = true)     // FK on PLANS
	  private Plan plan; 
	  
	  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL })
	  @JoinColumn(name = "member_id", nullable = true)     // FK on PAYMENTS
	  private Set<Payment> payments = new HashSet<>(); 
	  
    public Member() {
    	
    }
    
    public Member(String name, int age, String address, String goal, Plan plan, Collection<Payment> payments ) {
    	this.name = name;
    	this.age = age;
    	this.address = address;
    	this.goal = goal;
		this.plan = plan;
		if (payments != null) this.payments.addAll(payments);
    }

    @XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@XmlElement
	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@XmlElement
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}
