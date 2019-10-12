/**
 * 
 */
package com.cg.frs.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Schedule;

/**
 * @author: DEVANG
 * description: Schedule Repository
 * created date: 09/10/2019
 * modified: -
 */
@Repository("scheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule,BigInteger> {

}