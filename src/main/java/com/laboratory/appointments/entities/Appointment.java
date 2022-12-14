package com.laboratory.appointments.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_APPOINTMENT", updatable=false, nullable=false)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern= "dd/MM/yyyy")
	@Column(name="DATE_APPOINTMENT")
	private LocalDate date;
	
	@JsonFormat(pattern = "HH:mm")
	@DateTimeFormat(pattern="HH:mm")
	@Column(name="HOUR_APPOINTMENT")
	private LocalTime hour;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@Basic(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TEST")
	private Tests idTest;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@Basic(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_AFFILIATE")	
	private Affiliate idAffiliate;
	
	
	public Appointment() {
		
	}


	public Appointment(Integer id, LocalDate date, LocalTime hour, Tests idTest, Affiliate idAffiliate) {
		super();
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.idTest = idTest;
		this.idAffiliate = idAffiliate;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getHour() {
		return hour;
	}


	public void setHour(LocalTime hour) {
		this.hour = hour;
	}


	public Tests getIdTest() {
		return idTest;
	}


	public void setIdTest(Tests idTest) {
		this.idTest = idTest;
	}


	public Affiliate getIdAffiliate() {
		return idAffiliate;
	}


	public void setIdAffiliate(Affiliate idAffiliate) {
		this.idAffiliate = idAffiliate;
	}
	
}
