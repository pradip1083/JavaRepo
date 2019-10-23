/**
 * 
 */
package com.example.notes.Notes.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author prnikam
 *
 */
@Entity
@Table(name="emplo")
@EntityListeners(AuditingEntityListener.class)

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="F_NAME", nullable=false)
	private String f_name;
	
	@Column(name="L_NAME", nullable=false)
	private String l_name;

	@OneToMany(mappedBy="emp",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Note> note = new HashSet<>();
	 

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Note> getNote() {
		return note;
	}

	public void setNote(Set<Note> note) {
		this.note = note;
	}

	

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */
		
	
}
