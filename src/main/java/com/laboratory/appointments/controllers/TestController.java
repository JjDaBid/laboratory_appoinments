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
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.appointments.entities.Tests;
import com.laboratory.appointments.services.implement.TestServiceImplement;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	@Qualifier("testServiceImplement")
	private TestServiceImplement testService;
	
	@GetMapping(path="/tests")
	public ResponseEntity<?> getList(){		
			try {
				List<Tests> allTests = testService.getList();
				if(allTests.isEmpty()) {
					return ResponseEntity.noContent().build();
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(allTests);
				}
			}catch (Exception ex) {
				return ResponseEntity.noContent().build();
			}
	}
	
	
	@GetMapping(path="/tests/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id){
		try {
			Tests testFound = testService.getById(id);			
			
			if(testFound == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(testFound);
			}			
		}catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@PostMapping(path="/tests")
	public ResponseEntity<?> post(@RequestBody Tests test){
		try {
			Tests newTest = testService.save(test);
			return ResponseEntity.status(HttpStatus.CREATED).body(newTest);	
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}			
	}
	
	@PutMapping(path="/tests", produces={"application/json"})
	public ResponseEntity<?> put(@RequestBody Tests test){
		try {
			testService.update(test);			
			return ResponseEntity.status(HttpStatus.CREATED).body(test);
		}catch (Exception ex){
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping(path="/tests/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		try {
			testService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex){
			return ResponseEntity.notFound().build();
		}		
	}

}
