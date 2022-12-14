package com.laboratory.appointments.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laboratory.appointments.entities.Tests;

@Service	
public interface TestService {
	
	public abstract List<Tests> getList();
	
	public abstract Tests getById(Integer id);
	
	public abstract Tests save(Tests test);
	
	public abstract Tests update(Tests test);	
	
	public abstract void delete(Integer id);
}