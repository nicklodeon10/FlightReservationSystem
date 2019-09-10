import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.ScheduleFlightServiceImpl;

class ScheduleFlightServiceTest {

	ScheduleFlightService scheduleFlightService;
	
	@BeforeAll
	public void beforeTest() {
		scheduleFlightService=new ScheduleFlightServiceImpl();
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
