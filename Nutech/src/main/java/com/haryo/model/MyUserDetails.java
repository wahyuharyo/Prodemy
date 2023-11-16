package com.haryo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails{
	private static final long serialVersionUID = -8620070235634249535L;
	
	private User user;
	private List<Role> roles;
	
	public MyUserDetails(User user, List<Role> roles) {
		this.user = user;
		this.roles = roles;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> result = new ArrayList<>();
		
		for (Role r : roles) {
			
			@SuppressWarnings("serial")
			GrantedAuthority GA = new GrantedAuthority() {
	
				@Override
				public String getAuthority() {
					return r.getId();
				}
			};
			result.add(GA);
		}
		return result;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
