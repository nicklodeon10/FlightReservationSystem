package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dao.AirportDaoImpl;
import com.cg.frs.dto.Airport;

public class AirportServiceImpl implements AirportService {

	AirportDaoImpl dao=new AirportDaoImpl();
	
	public List<Airport> viewAirport() {
		// TODO Auto-generated method stub
		return dao.viewAirport();
	}

	public Airport viewAirport(String s) {
		// TODO Auto-generated method stub
		return dao.viewAirport(s);
	}

}
