/**
 * 
 */
package com.cg.frs.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Booking;

/**
 * @author: DEVANG
 * description: Booking Repository 
 * created date: 09/10/2019
 * modified: 09/10/2019
 */
@Repository("bookingDao")
public interface BookingRepository extends JpaRepository<Booking, BigInteger> {
	
	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves bookings by user id.
	 *  Input: User Id
	 *  Output: List of Bookings
	 *  Created Date: 11/10/2019
	 *  Last Modified: -
	 */
	List<Booking> findByUserId(BigInteger userId);
	
}
