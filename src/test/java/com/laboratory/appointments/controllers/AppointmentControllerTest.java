package com.laboratory.appointments.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.entities.Appointment;
import com.laboratory.appointments.entities.Tests;
import com.laboratory.appointments.services.implement.AppointmentServiceImplement;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {
	
	@Mock
	AppointmentServiceImplement serviceAppointmentMock;
	
	@InjectMocks
	AppointmentController appointmentController = new AppointmentController();
	

//	Testing appointments list
	@Test
	public void testGetEmptyAppointments() {
		when(serviceAppointmentMock.getList()).thenReturn(Collections.emptyList());
		var response = appointmentController.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
//	Testing get appointments list
	@Test
	public void testGetAppointmentList() {
		List<Appointment> appointment = new ArrayList<Appointment>();
		appointment.add(new Appointment());	
		when(serviceAppointmentMock.getList()).thenReturn(appointment);
		var response = appointmentController.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
//	Testing get appointment by ID when the appointment doesn't exists
	@Test
	public void testNotFoundGetAppointmentById() {
		when(serviceAppointmentMock.getById(anyInt())).thenReturn(null);
		var response = appointmentController.getById(anyInt());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing get appointment by ID
	@Test
	public void testGetAppointmentsById() {
		LocalTime appHour = LocalTime.now();
		LocalDate appDate = LocalDate.now();
		Affiliate aff = new Affiliate();
		Tests tes = new Tests();

		Appointment appointment = new Appointment(1, appDate, appHour, tes, aff);
		when(serviceAppointmentMock.getById(anyInt())).thenReturn(appointment);
		var response = appointmentController.getById(appointment.getId());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
//	Testing save the appointment when there's an exception
	@Test
	public void testNonPostAppointment() {
		Appointment newAppointment = new Appointment();
		when(serviceAppointmentMock.save(newAppointment)).thenReturn(newAppointment);
		var response = appointmentController.post(null);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing save an appointment properly 
	@Test
	public void testPostAppointment() {
		LocalTime appHour = LocalTime.now();
		LocalDate appDate = LocalDate.now();
		Affiliate aff = new Affiliate();
		Tests tes = new Tests();
		Appointment newAppointment = new Appointment(1, appDate, appHour, tes, aff);		
		
		when(serviceAppointmentMock.save(newAppointment)).thenReturn(newAppointment);
		var response = appointmentController.post(newAppointment);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
// 	Testing exception updating an appointment
	@Test
	public void testPutEmptyAppointment() {
		Appointment currentAppointment = new Appointment();		
		when(serviceAppointmentMock.update(null)).thenReturn(null);		
		var response = appointmentController.put(currentAppointment);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing update appointment
	@Test
	public void testPutAppointment() {
		Appointment currentAppointment = new Appointment();		
		when(serviceAppointmentMock.update(any(Appointment.class))).thenReturn(currentAppointment);		
		var response = appointmentController.put(currentAppointment);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

//	Testing exception deleting an appointment 
	@Test
	public void testDeleteEmptyAppointment() {
		doThrow(new RuntimeException()).when(serviceAppointmentMock).delete(anyInt());
		var response = appointmentController.delete(anyInt());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing delete an appointment
	@Test
	public void testDeleteAffiliate() {
		doNothing().when(serviceAppointmentMock).delete(anyInt());
		var response = appointmentController.delete(anyInt());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
//	Testing query appointments by date when there are no appointments 
	@Test
	public void testGetEmptyListByDatequery() {
		when(serviceAppointmentMock.getByDate("05/12/2022")).thenReturn(Collections.emptyList());
		var response = appointmentController.getByDate("05/12/2022");
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing query appointments by date when there are results
	@Test
	public void testGetByDateQuery() {
		List<Appointment> appointment = new ArrayList<Appointment>();
		appointment.add(new Appointment());	
		when(serviceAppointmentMock.getByDate("05/12/2022")).thenReturn(appointment);
		var response = appointmentController.getByDate("05/12/2022");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
//	Testing query appointments by affiliate when there are no appointments 
	@Test
	public void testGetEmptyListByAffiliateQuery() {
		Affiliate affiliate = new Affiliate();
		when(serviceAppointmentMock.getAppointmentByAffiliate(affiliate)).thenReturn(Collections.emptyList());
		var response = appointmentController.getAppointmentByAffiliate(affiliate);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
//	Testing query appointments by affiliate when there are results 
	@Test
	public void testGetByAffiliateQuery() {
		List<Appointment> appointments = new ArrayList<Appointment>();
		appointments.add(new Appointment());
		Affiliate affiliate = new Affiliate();
		when(serviceAppointmentMock.getAppointmentByAffiliate(affiliate)).thenReturn(appointments);
		var response = appointmentController.getAppointmentByAffiliate(affiliate);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	

}
