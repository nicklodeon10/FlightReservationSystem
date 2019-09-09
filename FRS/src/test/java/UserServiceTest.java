import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.dto.Flight;

class UserServiceTest {

	UserService userService;
	
	@BeforeAll
	public void beforeTest() {
		userService=new UserServiceImpl();
	}
	
	@Test
	public void testAddUser() {
		
	}
	
	@Test
	public void testModifyUser() {
		
	}
	
	public void testViewUser() {
		
	}
	
	public void testDeleteUser() {
		
	}
	
	@AfterAll
	public void afterTest() {
		userService=null;
	}

}
