package com.cg.frs.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.ScheduleFlight;

@Repository
public interface ScheduleFlightRepository extends JpaRepository<ScheduleFlight,BigInteger> {

		
}