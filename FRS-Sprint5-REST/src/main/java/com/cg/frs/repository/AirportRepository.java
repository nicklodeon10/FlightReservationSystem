/**
 * 
 */
package com.cg.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Airport;

/**
 * @author: DEVANG
 * description: Airport Repository 
 * created date: 09/10/2019
 * modified: -
 */
@Repository("airportDao")
public interface AirportRepository extends JpaRepository<Airport, String> {

}
