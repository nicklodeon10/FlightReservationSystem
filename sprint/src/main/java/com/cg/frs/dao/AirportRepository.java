package com.cg.frs.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport,String> {
	

		
}
