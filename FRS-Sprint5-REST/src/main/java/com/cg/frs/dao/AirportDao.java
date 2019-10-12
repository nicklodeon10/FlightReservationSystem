/**
 * 
 */
package com.cg.frs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Airport;

/**
 * @author DEVANG
 *
 */

@Repository("airportDao")
public interface AirportDao extends JpaRepository<Airport, String> {

}
