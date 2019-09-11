package com.cg.frs.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Airport;

public class AirportDao implements IAirportDao{

	private List<Airport> airportList=new ArrayList<Airport>();
	
	@Override
	public void addAirport() {
		Airport a1=new Airport("Mumbai Airport","Mumbai","MUM");
		Airport a2=new Airport("Delhi Airport","Delhi","DEL");
		Airport a3=new Airport("Kolkata Airport","Kolkata","KOL");
		Airport a4=new Airport("Chennai Airport","Chennai","CHE");
		airportList.add(a1);
		airportList.add(a2);
		airportList.add(a3);
		airportList.add(a4);
	}
	
	@Override
	public List<Airport> viewAirport() {
		return airportList;
	}	
	
}
