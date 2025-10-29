package com.app.community_centre_ca.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@XmlRootElement(name = "plan")
@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "Plan_Description", nullable = true, length = 100)
	private String type;
	
	@Column(name = "Plan_Cost", nullable = true, length = 100)
	private int cost; 
	  
	  public Plan() {
		  
	  }
	  
	  public Plan(String type, int cost) {
		  this.type = type;
		  this.cost = cost;
	  }

	  @XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	    
}
