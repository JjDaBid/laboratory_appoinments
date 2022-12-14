package com.laboratory.appointments.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laboratory.appointments.entities.Affiliate;

@Service
public interface AffiliateService {
	
	public abstract List<Affiliate> getList();
	
	public abstract Affiliate getById(Integer id);
	
	public abstract Affiliate save(Affiliate affiliate);
	
	public abstract Affiliate update(Affiliate affiliate);	
	
	public abstract void delete(Integer id);
}