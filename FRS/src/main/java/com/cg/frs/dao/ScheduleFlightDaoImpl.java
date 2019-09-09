package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.ScheduleFlight;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao{

	private List<ScheduleFlight> scheduleFlightList=new ArrayList<ScheduleFlight>();
	
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleFlight) {
		scheduleFlightList.add(scheduleFlight);
		return scheduleFlight;
	}

	public List<ScheduleFlight> viewScheduleFlight() {
		return scheduleFlightList;
	}

	public void deleteScheduleFlight(BigInteger flightId) {
		for(ScheduleFlight scheduleFlight: scheduleFlightList) {
			if(scheduleFlight.getFlight().getFlightNumber()==flightId){
				scheduleFlightList.remove(scheduleFlight);
				break;
			}
		}	
	}
}
