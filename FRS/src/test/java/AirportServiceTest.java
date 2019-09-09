import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AirportServiceTest {

	AirportService airportService;
	
	@BeforeAll
	public void beforeTest() {
		airportService=new AirportServiceImpl();
	}
	
	public void testViewAirport() {
		
	}
	
	@AfterAll
	public void afterTest() {
		airportService=null;
	}
}
