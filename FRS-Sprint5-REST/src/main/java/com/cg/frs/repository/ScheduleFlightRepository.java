/**
 * 
 */
package com.cg.frs.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.ScheduleFlight;

/**
 * @author: DEVANG
 * description: ScheduleFlight Repository 
 * created date: 09/10/2019
 * modified: -
 */
@Repository("scheduleFlightRepository")
public interface ScheduleFlightRepository extends JpaRepository<ScheduleFlight,BigInteger> {

		
}