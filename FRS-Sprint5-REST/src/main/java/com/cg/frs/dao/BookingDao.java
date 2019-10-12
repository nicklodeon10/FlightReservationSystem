/**
 * 
 */
package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Booking;

/**
 * @author DEVANG
 *
 */

@Repository("bookingDao")
public interface BookingDao extends JpaRepository<Booking, BigInteger> {

	List<Booking> findByUserId(BigInteger userId);
	
}
