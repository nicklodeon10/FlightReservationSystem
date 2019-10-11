package com.cg.frs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.AirportRepository;
import com.cg.frs.dto.Airport;
import com.cg.frs.exception.FrsException;
@Service("airportservice")
public class AirportServiceImpl implements AirportService{
	@Autowired
	AirportRepository airportRepository;
	/*
	 * Author Surya
	 * Created on 08/10/2019
	 * Last modified on 10/10/2019
	 * view airport
	 * Input ---
	 * Output airport list
	 */
	@Override
	public List<Airport> viewAirport() {
		return airportRepository.findAll();
	}
	/*
	 * Author Surya
	 * Created on 08/10/2019
	 * Last modified on 10/10/2019
	 * view airport
	 * Input string
	 * Output Airport object
	 */
	@Override
	public Airport viewAirport(String source) throws FrsException {
		if(source==null)
			throw new FrsException("Enter Airport Code");
		Airport airport=airportRepository.findByAirportLocation(source);
		if(airport==null)
			throw new FrsException("Enter Airport Code");
		else
			return airport;
	}
	/*
	 * Author Surya
	 * Created on 08/10/2019
	 * Last modified on 10/10/2019
	 * validate airportcode
	 * Input string
	 * Output boolean
	 */
	@Override
	public boolean validateAirportWithCode(String airportCode) throws FrsException {
		if(airportCode==null)
			throw new FrsException("Enter Airport Code");
		return airportRepository.existsById(airportCode);
	}
	/*
	 * Author Surya
	 * Created on 08/10/2019
	 * Last modified on 10/10/2019
	 * compare source and destination
	 * Input airport object,airport object
	 * Output boolean
	 */
	@Override
	public boolean compareAirport(Airport src, Airport dest) throws FrsException  {
		if(src.equals(dest))
			throw new FrsException("Enter Valid Source and Destination Airports");
		return false;
	}

}
