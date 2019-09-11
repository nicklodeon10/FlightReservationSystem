import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.service.IScheduleFlightService;
import com.cg.frs.service.ScheduleFlightService;

class ScheduleFlightServiceTest {

	IScheduleFlightService scheduleFlightService;
	
	@BeforeAll
	public void beforeTest() {
		scheduleFlightService=new ScheduleFlightService();
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
