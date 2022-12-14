package com.laboratory.appointments.services.implement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.entities.Appointment;
import com.laboratory.appointments.repositories.AppointmentRepository;
import com.laboratory.appointments.services.AppointmentService;

@Service("appointmentServiceImplement")
public class AppointmentServiceImplement implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRespository;

	@Override
	public List<Appointment> getList() {
		return appointmentRespository.findAll();
	}

	@Override
	public Appointment getById(Integer id) {
		return appointmentRespository.findById(id).orElseThrow();		
	}

	@Override
	public Appointment save(Appointment appointment) {
		return appointmentRespository.save(appointment);
	}
	
	@Override
	public Appointment update(Appointment appointment) {
		Optional<Appointment> currentAppointment = appointmentRespository.findById(appointment.getId());
		currentAppointment.get().setDate(appointment.getDate());
		currentAppointment.get().setHour(appointment.getHour());
		currentAppointment.get().setIdTest(appointment.getIdTest());
		currentAppointment.get().setIdAffiliate(appointment.getIdAffiliate());
		return appointmentRespository.save(appointment);
	}
	
	@Override
	public void delete(Integer id) {
		Optional<Appointment> appointmentFound = appointmentRespository.findById(id);
		if(appointmentFound == null) {
			ResponseEntity.notFound().build();
		} else {
			appointmentRespository.deleteById(id);
		}
				
	}
	
	@Override
	public List<Appointment>getByDate(String date){
		LocalDate localDate = LocalDate.parse(date);
		return appointmentRespository.findByDateOrderByIdAffiliateAsc(localDate);
	}

	@Override
	public List<Appointment> getAppointmentByAffiliate(Affiliate idAffiliate) {

		return appointmentRespository.getAppointmentByIdAffiliate(idAffiliate);
	}
	
}