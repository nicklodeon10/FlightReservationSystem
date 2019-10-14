package com.cg.frs.dao;
/*
 * Author Surya
 * Created on 08/10/2019
 * Last modified on 10/10/2019
 */
import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.ScheduleFlight;

@Repository
public interface ScheduleFlightRepository extends JpaRepository<ScheduleFlight,BigInteger> {

		
}