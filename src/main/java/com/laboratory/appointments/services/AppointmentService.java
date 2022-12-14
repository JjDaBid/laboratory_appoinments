package com.laboratory.appointments.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.entities.Appointment;

@Service
public interface AppointmentService {

	public abstract List<Appointment> getList();
	
	public abstract Appointment getById(Integer id);
	
	public abstract Appointment save(Appointment appointment);
	
	public abstract Appointment update(Appointment appointment);	
	
	public abstract void delete(Integer id);

	List<Appointment> getByDate(String date);

	List<Appointment> getAppointmentByAffiliate(Affiliate idAffiliate);

}