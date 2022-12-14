package com.laboratory.appointments.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Affiliates")
public class Affiliate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_AFFILIATE")
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private String mail;
	
	
	public Affiliate() {
		
	}
	
	public Affiliate(String name, Integer age, String mail) {
		super();
		this.name = name;
		this.age = age;
		this.mail = mail;
	}


	public Affiliate(Integer id, String name, Integer age, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.mail = mail;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
