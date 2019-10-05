package com.cg.frs.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Airport;

public class AirportDaoImpl implements AirportDao{

	private static List<Airport> airportList=new ArrayList<Airport>();
	
	@Override
	public List<Airport> viewAirport() {
		return airportList;
	}	
	
}
