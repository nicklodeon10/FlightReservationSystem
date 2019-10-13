/**
 * 
 */
package com.cg.frs.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.User;

/**
 * @author: DEVANG
 * description: User Repository  
 * created date: 09/10/2019
 * modified: 11/10/2019
 */
@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
	
	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves user by user name.
	 *  Input: Username String.
	 *  Output: Optional storing User.
	 *  Created Date: 11/10/2019
	 *  Last Modified: -
	 */
	Optional<User> findByUserName(String userName);
	
}
