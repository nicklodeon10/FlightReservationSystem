import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.cg.frs.service.IAirportService;
import com.cg.frs.service.AirportService;

class AirportServiceTest {

	IAirportService airportService;
	
	@BeforeAll
	public void beforeTest() {
		airportService=new AirportService();
	}
	
	public void testViewAirport() {
		
	}
	
	@AfterAll
	public void afterTest() {
		airportService=null;
	}
}
