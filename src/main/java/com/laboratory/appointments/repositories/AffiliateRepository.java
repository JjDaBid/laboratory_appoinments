package com.laboratory.appointments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratory.appointments.entities.Affiliate;

@Repository("affiliatesRepository")
public interface AffiliateRepository extends JpaRepository<Affiliate, Integer>{

}