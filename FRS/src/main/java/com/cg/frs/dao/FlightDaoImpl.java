package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cg.frs.dto.Flight;

public class FlightDaoImpl implements FlightDao {

	List<Flight> flightList=new ArrayList<Flight>();
	
	public Flight addFlightService(Flight flight) {
		// TODO Auto-generated method stub
		
		flightList.add(flight);
		
		return flight;
	}

	public Flight modifyFlightService(Flight flight) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		Flight modFlight=new Flight();
		
		modFlight=viewFlight(flight.getFlightNumber())
=======
		
>>>>>>> 575ca4c8b61aee28607b8cf35b2494511d6a0d01
		
		
		
		
		return flight;
	}

	public List<Flight> viewFlight() {
		// TODO Auto-generated method stub
		
		
		for (int i = 0; i < flightList.size(); i++) {
			System.out.println(flightList.get(i));
		}
		
		return flightList;
	}

	public List<Flight> viewFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		
		
		for(Flight i:flightList) {
			
			if(i.getFlightNumber()==flightId) {
				System.out.println("Flight Found"+i);
				break;
			}
			
		}
			
		
		
		return flightList;
	}

	public void deleteFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		int count=0;
         for(Flight i:flightList) {
			count++;
			if(i.getFlightNumber()==flightId) {
				
				
				
				flightList.remove(flightList.get(count));
				System.out.println("Flight deleted"+i);
				break;
			}
			
		}
		
		
	}












}
