import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.service.IFlightService;
import com.cg.frs.service.FlightService;

class FlightServiceTest {

	IFlightService flightService;
	
	@BeforeAll
	public void beforeTest() {
		flightService=new FlightService();
	}
	
	@Test
	public void testAddFlight() {
		
	}
	
	@Test
	public void testModifyFlight() {
		
	}
	
	public void testViewFlight() {
		
	}
	
	public void testDeleteFlight() {
		
	}
	
	@AfterAll
	public void afterTest() {
		flightService=null;
	}

}
