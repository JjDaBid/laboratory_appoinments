package com.laboratory.appointments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratory.appointments.entities.Tests;

@Repository("testRepository")
public interface TestRepository extends JpaRepository<Tests, Integer>{

}