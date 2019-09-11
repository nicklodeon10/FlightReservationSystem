import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.service.IBookingService;
import com.cg.frs.service.BookingService;

class BookingServiceTest {

	IBookingService bookingService;
	
	@BeforeAll
	public void beforeTest() {
		bookingService=new BookingService();
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
