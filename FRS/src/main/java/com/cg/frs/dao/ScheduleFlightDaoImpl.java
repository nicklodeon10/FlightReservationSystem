package com.cg.frs.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao{

	List<ScheduleFlight> scheduleFlightList=new ArrayList<ScheduleFlight>();
	
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleFlight) {
		// TODO Auto-generated method stub
		scheduleFlightList.add(scheduleFlight)
		
		
		return scheduleFlight;
	}

	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate) {
		// TODO Auto-generated method stub
		
          for(ScheduleFlight i:scheduleFlightList) {
			
<<<<<<< HEAD
			if(i.getFlightNumber()==flightId) {
=======
			if(i.()==flightId) {
>>>>>>> 575ca4c8b61aee28607b8cf35b2494511d6a0d01
				System.out.println("Flight Found"+i);
				
			}else System.out.println("Flight not found");
			
		}
		
		
		
		return null;
	}

	public ScheduleFlight viewScheduleFlights(BigInteger flightId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ScheduleFlight> viewScheduleFlight() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScheduleFlight modifyScheduleFlight(Flight flight, Schedule schedule, Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteScheduleFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		
	}

}
