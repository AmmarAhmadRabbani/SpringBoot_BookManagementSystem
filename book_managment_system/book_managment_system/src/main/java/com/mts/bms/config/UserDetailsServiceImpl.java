package com.mts.bms.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mts.bms.entity.User;
import com.mts.bms.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmailId(username); // fetching user from db
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("could not found user");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
		return customUserDetails;

	}

}
