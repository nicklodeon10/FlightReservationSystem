import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.dto.Flight;

class FlightServiceTest {

	FlightService flightService;
	
	@BeforeAll
	public void beforeTest() {
		flightService=new FlightServiceImpl();
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
