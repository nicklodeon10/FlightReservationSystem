import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.frs.service.IUserService;
import com.cg.frs.service.UserService;

class UserServiceTest {

	IUserService userService;
	
	@BeforeAll
	public void beforeTest() {
		userService=new UserService();
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
