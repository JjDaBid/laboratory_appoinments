package com.laboratory.appointments.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.entities.Appointment;

@Repository("appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	List<Appointment>getAppointmentByIdAffiliate(Affiliate idAffiliate);
	
	List<Appointment>findByDateOrderByIdAffiliateAsc(LocalDate date);	
	
}