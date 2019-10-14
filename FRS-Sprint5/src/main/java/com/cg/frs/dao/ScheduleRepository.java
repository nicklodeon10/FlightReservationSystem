package com.cg.frs.dao;
/*
 * Author Surya
 * Created on 08/10/2019
 * Last modified on 10/10/2019
 */
import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.frs.dto.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,BigInteger> {

}
