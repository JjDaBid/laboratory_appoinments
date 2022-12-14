package com.laboratory.appointments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.appointments.entities.Affiliate;
import com.laboratory.appointments.services.implement.AffiliateServiceImplement;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class AffiliateController {
	
	@Autowired
	@Qualifier("affiliateServiceImplement")
	private AffiliateServiceImplement affiliatesService;
	
	@GetMapping(path="/affiliates")
	public ResponseEntity<?> getList(){
			try {
				List<Affiliate> allAffiliates = affiliatesService.getList();
				if(allAffiliates.isEmpty()) {
					return ResponseEntity.noContent().build();
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(allAffiliates);
				}
			}catch (Exception ex) {
				return ResponseEntity.noContent().build();
			}
	}
	
	@GetMapping(path="/affiliates/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id){
		try {
			Affiliate affiliateFound = affiliatesService.getById(id);			
			
			if(affiliateFound == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(affiliateFound);
			}			
		}catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}	
	}	
	
	@PostMapping(path="/affiliates")
	public ResponseEntity<?> post(@RequestBody Affiliate affiliate){
		try {
			Affiliate newAffiliate = affiliatesService.save(affiliate);
			return ResponseEntity.status(HttpStatus.CREATED).body(newAffiliate);	
		} catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	
	@PutMapping(path="/affiliates", produces={"application/json"})
	public ResponseEntity<?> put(@RequestBody Affiliate affiliate){		
		try {			
			affiliatesService.update(affiliate);
			return ResponseEntity.status(HttpStatus.CREATED).body(affiliate);
		}catch (Exception ex){
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping(path="/affiliates/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		try {
			affiliatesService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex){
			return ResponseEntity.notFound().build();
		}		
	}

}
