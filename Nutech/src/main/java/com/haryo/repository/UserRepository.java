/**
 * 
 */
package com.haryo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haryo.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
