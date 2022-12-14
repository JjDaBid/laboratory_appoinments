package com.laboratory.appointments.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratory.appointments.entities.Tests;
import com.laboratory.appointments.repositories.TestRepository;
import com.laboratory.appointments.services.TestService;

@Service("testServiceImplement")
public class TestServiceImplement implements TestService{
	
	@Autowired
	private TestRepository testRespository;
	
	@Override
	public List<Tests> getList() {
		return testRespository.findAll();
	}

	@Override
	public Tests getById(Integer id) {
		return testRespository.findById(id).orElseThrow();
	}

	@Override
	public Tests save(Tests test) {
		return testRespository.save(test);
	}
	
	@Override
	public Tests update(Tests test) {
		Optional<Tests> currentTest = testRespository.findById(test.getId());
		currentTest.get().setName(test.getName());
		currentTest.get().setDescription(test.getDescription());
		return testRespository.save(test);
	}
	
	@Override
	public void delete(Integer id) {
		testRespository.deleteById(id);
	}
	
}