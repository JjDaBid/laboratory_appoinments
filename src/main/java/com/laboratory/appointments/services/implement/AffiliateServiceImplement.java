package com.laboratory.appointments.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.repositories.AffiliateRepository;
import com.laboratory.appointments.services.AffiliateService;

@Service("affiliateServiceImplement")
public class AffiliateServiceImplement implements AffiliateService{
	
	@Autowired
	@Qualifier("affiliatesRepository")
	private AffiliateRepository affiliateRepository;

	@Override
	public List<Affiliate> getList() {
		return affiliateRepository.findAll();
	}

	@Override
	public Affiliate getById(Integer id) {
		return affiliateRepository.findById(id).orElseThrow();
	}

	@Override
	public Affiliate save(Affiliate affiliate) {
		return affiliateRepository.save(affiliate);
	}
	
	@Override
	public Affiliate update(Affiliate affiliate) {
		Optional<Affiliate> currentAffiliate = affiliateRepository.findById(affiliate.getId());
		currentAffiliate.get().setName(affiliate.getName());
		currentAffiliate.get().setAge(affiliate.getAge());
		currentAffiliate.get().setMail(affiliate.getMail());
		return affiliateRepository.save(affiliate);
	}
	
	
	@Override
	public void delete(Integer id) {
		affiliateRepository.deleteById(id);	
	}

}
