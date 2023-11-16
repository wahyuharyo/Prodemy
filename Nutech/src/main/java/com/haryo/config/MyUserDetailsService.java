package com.haryo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.haryo.model.MyUserDetails;
import com.haryo.model.Role;
import com.haryo.model.User;
import com.haryo.model.UserRole;
import com.haryo.repository.UserRepository;
import com.haryo.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	
	private UserRoleRepository userRoleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Mengambil Data User
		User user = this.userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not Found with username: " + username);
		}

		try {
			// Mengambil data UserRole
			List<UserRole> userRoles = this.userRoleRepo.findByIdUserId(username);
			List<Role> roles = new ArrayList<>();
			
			for (UserRole ur : userRoles) {
				Role r = new Role();
				r.setId(ur.getRoleId());
				roles.add(r);
			}
			
			return new MyUserDetails(user, roles);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
