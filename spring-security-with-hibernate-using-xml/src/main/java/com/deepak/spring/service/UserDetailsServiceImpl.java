package com.deepak.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.deepak.spring.dao.UserDao;
import com.deepak.spring.model.UserRole;

public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.deepak.spring.model.User user = userDao.getUserByName(username);

		List<GrantedAuthority> authorities =  buildGrantedAuthorities(user.getUserRoles());
		return buildUserForAuthentication(user,authorities);
	}
	
	//Convert User to org.springframework.security.core.userdetails.User
	//This Spring User Contains 7 parameters
	private User buildUserForAuthentication(com.deepak.spring.model.User user, List<GrantedAuthority> authorities){
		return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
	}
	
	//Convert UserRole into List of GrantedAuthority
	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auths);

		return authorities;
	}

}
