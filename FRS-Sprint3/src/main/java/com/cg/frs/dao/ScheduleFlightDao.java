package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.ScheduleFlight;

public interface ScheduleFlightDao {

	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight);
	
	public List<ScheduleFlight> viewScheduleFlight();
	
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleflight);
    
	public boolean deleteScheduleFlight(BigInteger flightId);
	
}
