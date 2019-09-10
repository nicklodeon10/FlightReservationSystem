import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.cg.frs.service.AirportService;
import com.cg.frs.service.AirportServiceImpl;

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
