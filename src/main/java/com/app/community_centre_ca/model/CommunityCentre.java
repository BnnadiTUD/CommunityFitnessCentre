package com.app.community_centre_ca.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@XmlRootElement(name = "CommunityCentre")
@Entity
public class CommunityCentre {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "Name_of_Community_Centre", nullable = true, length = 100)
	private String name;
	
	@Column(name = "Address_of_Community_Centre", nullable = true, length = 100)
	private String address;
	
	  @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	  @JoinColumn(name = "centre_id", nullable = true)     // FK on MEMBERS
	 private Set<Member> members = new HashSet<>(); 
	  
	  public CommunityCentre() {
		  
	  }
	  
	  public CommunityCentre(String name, String address, Collection<Member> members) {
		  this.name = name;
		  this.address = address;
		  if (members != null) this.members.addAll(members);
	  }

@XmlElement	  
	  public long getId() {
		return id;
	}

	  public void setId(long id) {
		  this.id = id;
	  }

	  @XmlElement
	  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}
