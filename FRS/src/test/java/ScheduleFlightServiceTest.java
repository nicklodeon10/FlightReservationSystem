import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.dto.Flight;

class ScheduleFlightServiceTest {

	ScheduleFlightService scheduleFlightService;
	
	@BeforeAll
	public void beforeTest() {
		scheduleFlightService=new Flight();
	}
	
	@Test
	public void testAddScheduleFlight() {
		
	}
	
	@Test
	public void testModifyScheduleFlight() {
		
	}
	
	public void testViewScheduleFlight() {
		
	}
	
	public void testDeleteScheduleFlight() {
		
	}
	
	@AfterAll
	public void afterTest() {
		scheduleFlightService=null;
	}

}
