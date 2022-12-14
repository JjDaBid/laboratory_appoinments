package com.laboratory.appointments.controllers;

import static org.mockito.Mockito.*;

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
import com.laboratory.appointments.services.implement.AffiliateServiceImplement;

@ExtendWith(MockitoExtension.class)
class AffiliateControllerTest {
		
	
	@Mock
	private AffiliateServiceImplement serviceAffiliateMock;
	
	
	@InjectMocks
	AffiliateController affiliateController = new AffiliateController();	
	
//	Testing empty affiliates list
	@Test
	public void testGetEmptyAffiliates() {
		when(serviceAffiliateMock.getList()).thenReturn(Collections.emptyList());
		var response = affiliateController.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
//	Testing get affiliates list
	@Test
	public void testGetAffiliateList() {
		List<Affiliate> affiliates = new ArrayList<Affiliate>();
		affiliates.add(new Affiliate());	
		when(serviceAffiliateMock.getList()).thenReturn(affiliates);
		var response = affiliateController.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

//	Testing get affiliate by ID when the affiliate doesn't exists
	@Test
	public void testNotFoundGetAffiliatesById() {
		Affiliate affiliateExpected = new Affiliate(5, "David", 39, "mail@mail");
		when(serviceAffiliateMock.getById(null)).thenReturn(null);
		var response = affiliateController.getById(affiliateExpected.getId());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing get affiliate by ID
	@Test
	public void testGetAffiliatesById() {
		Affiliate affiliateExpected = new Affiliate(5, "David", 25, "mail@mail");
		when(serviceAffiliateMock.getById(anyInt())).thenReturn(affiliateExpected);
		var response = affiliateController.getById(affiliateExpected.getId());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
//	Testing save the affiliate when there's an exception
	@Test
	public void testNonPostAffiliate() {
		Affiliate newAffiliate = new Affiliate();
		when(serviceAffiliateMock.save(newAffiliate)).thenReturn(newAffiliate);
		var response = affiliateController.post(null);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing save an affiliate properly 
	@Test
	public void testPostAffiliate() {
		Affiliate newAffiliate = new Affiliate(5, "David", 39, "mail@mail");
		when(serviceAffiliateMock.save(newAffiliate)).thenReturn(newAffiliate);
		var response = affiliateController.post(newAffiliate);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	
// 	Testing exception updating an affiliate
	@Test
	public void testPutEmptyAffiliate() {
		Affiliate currentAffiliate = new Affiliate();		
		when(serviceAffiliateMock.update(null)).thenReturn(null);		
		var response = affiliateController.put(currentAffiliate);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing update affiliate
	@Test
	public void testPutAffiliate() {
		Affiliate currentAffiliate = new Affiliate();		
		when(serviceAffiliateMock.update(any(Affiliate.class))).thenReturn(currentAffiliate);		
		var response = affiliateController.put(currentAffiliate);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
//	Testing exception deleting an affiliate 
	@Test
	public void testDeleteEmptyAffiliate() {
		doThrow(new RuntimeException()).when(serviceAffiliateMock).delete(anyInt());
		var response = affiliateController.delete(anyInt());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing delete an affiliate
	@Test
	public void testDeleteAffiliate() {
		doNothing().when(serviceAffiliateMock).delete(anyInt());
		var response = affiliateController.delete(anyInt());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}