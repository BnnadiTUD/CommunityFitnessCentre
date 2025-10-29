package com.app.community_centre_ca.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@XmlRootElement(name = "payment")
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "Payment_Date", nullable = true, length = 100)
	private String date;
	
	@Column(name = "Payment_Amount", nullable = true, length = 100)
	private int amount; 
	
	public Payment() {
		
	}
	
	public Payment(String date, int amount) {
		this.date = date;
		this.amount = amount;
	}

	@XmlElement
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@XmlElement
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
