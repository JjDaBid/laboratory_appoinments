package com.laboratory.appointments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.entities.Appointment;
import com.laboratory.appointments.services.implement.AppointmentServiceImplement;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
	@Qualifier("appointmentServiceImplement")
	private AppointmentServiceImplement appointmentService;
	
	
	@GetMapping(path="/appointments")
	public ResponseEntity<?> getList(){		
			try {
				List<Appointment> allAppointments = appointmentService.getList();
				if(allAppointments.isEmpty()) {
					return ResponseEntity.noContent().build();
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(allAppointments);
				}
			}catch (Exception ex) {
				return ResponseEntity.noContent().build();
			}
	}
	
	@GetMapping(path="/appointments/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id){
		try {
			Appointment appointmentFound = appointmentService.getById(id);			
			
			if(appointmentFound == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(appointmentFound);
			}			
		}catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@PostMapping(path="/appointments")
	public ResponseEntity<?> post(@RequestBody Appointment appointment){
		try {
			Appointment newAppointment = appointmentService.save(appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);	
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}			
	}
	
	@PutMapping(path="/appointments/", produces={"application/json"})
	public ResponseEntity<?> put(@RequestBody Appointment appointment){
		try {			
			appointmentService.update(appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
		}catch (Exception ex){
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path="/appointments/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		try {		
			appointmentService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex){
			return ResponseEntity.notFound().build();
		}		
	}
	
	@RequestMapping(path="/appointments/dates/{date}", method=RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getByDate(@PathVariable("date") String date){		
		List<Appointment> appoinmentsList = appointmentService.getByDate(date);
		if(appoinmentsList.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(appoinmentsList);
	}
	
	@RequestMapping(path="/appointments/byaffiliate/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getAppointmentByAffiliate(@PathVariable("id") Affiliate idAffiliate){		
		List<Appointment> appointmentsByAffiliate = appointmentService.getAppointmentByAffiliate(idAffiliate);		
		if(appointmentsByAffiliate.isEmpty()) {
			return ResponseEntity.noContent().build();		 
		} else {
			return ResponseEntity.ok(appointmentsByAffiliate);
		}
	}
		
}