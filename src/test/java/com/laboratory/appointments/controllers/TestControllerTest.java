package com.laboratory.appointments.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

import com.laboratory.appointments.entities.Tests;
import com.laboratory.appointments.services.implement.TestServiceImplement;

@ExtendWith(MockitoExtension.class)
class TestControllerTest {
	
	
	@Mock
	private TestServiceImplement testServiceMock;
	
	@InjectMocks
	private TestController testController = new TestController();

	
//	Testing empty list of tests
	@Test
	void testGetEmptyTestList() {
		when(testServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = testController.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
		
//	Testing list of tests
	@Test
	public void testGetTestList() {
		List<Tests> tests = new ArrayList<Tests>();
		tests.add(new Tests());	
		when(testServiceMock.getList()).thenReturn(tests);
		var response = testController.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}	
		
	
//	Testing get test by id when the test doesn't exists	
	@Test
	public void testNotFoundGetTestById() {
		Tests testExpected = new Tests(3, "Sangre", "descripcion examen de sangre");
		when(testServiceMock.getById(null)).thenReturn(null);
		var response = testController.getById(testExpected.getId());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing get test by ID.
	@Test
	public void testGetTestById() {
		Tests testExpected = new Tests(3, "Sangre", "descripcion examen de sangre");
		when(testServiceMock.getById(anyInt())).thenReturn(testExpected);
		var response = testController.getById(testExpected.getId());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	

// Testing Save test when there's an exception
	@Test
	public void testNonPostTest() {
		Tests newTests = new Tests();
		when(testServiceMock.save(newTests)).thenReturn(newTests);
		var response = testController.post(null);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing saving a test properly 
	@Test
	public void testPostTest() {
		Tests newTests = new Tests(3, "Sangre", "descripcion examen de sangre");
		when(testServiceMock.save(newTests)).thenReturn(newTests);
		var response = testController.post(newTests);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
// 	Testing exception updating a test
	@Test
	public void testPutEmptyTest() {
		Tests currentTests = new Tests();		
		when(testServiceMock.update(null)).thenReturn(null);		
		var response = testController.put(currentTests);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing update test
	@Test
	public void testPutTest() {
		Tests currentTests = new Tests();		
		when(testServiceMock.update(any(Tests.class))).thenReturn(currentTests);		
		var response = testController.put(currentTests);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
//	Testing exception deleting a test
	@Test
	public void testDeleteEmptyTest() {
		doThrow(new RuntimeException()).when(testServiceMock).delete(anyInt());
		var response = testController.delete(anyInt());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
//	Testing delete a test
	@Test
	public void testDeleteTest() {
		doNothing().when(testServiceMock).delete(anyInt());
		var response = testController.delete(anyInt());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}