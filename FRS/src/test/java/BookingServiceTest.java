import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.service.BookingService;
import com.cg.frs.service.BookingServiceImpl;

class BookingServiceTest {

	BookingService bookingService;
	
	@BeforeAll
	public void beforeTest() {
		bookingService=new BookingServiceImpl();
	}
	
	@Test
	public void testAddBooking() {
		
	}
	
	@Test
	public void testModifyBooking() {
		
	}
	
	public void testViewBooking() {
		
	}
	
	public void testDeleteBooking() {
		
	}
	
	@AfterAll
	public void afterTest() {
		bookingService=null;
	}

}