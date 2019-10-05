/**
 * 
 */
package com.cg.frs.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Booking;

/**
 * @author DEVANG
 *
 */

@Repository("bookingDao")
public interface BookingDao extends JpaRepository<Booking, BigInteger> {

}
