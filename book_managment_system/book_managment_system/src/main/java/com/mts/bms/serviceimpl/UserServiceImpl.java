package com.mts.bms.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mts.bms.dto.UserDto;
import com.mts.bms.dto.UserUpdateDto;
import com.mts.bms.entity.Role;
import com.mts.bms.entity.User;
import com.mts.bms.repository.RoleRepository;
import com.mts.bms.repository.UserRepository;
import com.mts.bms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto userRegister(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		if (roleRepository.findAll().isEmpty()) {
			Role role1 = new Role();
			Role role2 = new Role();
			Role role3 = new Role();
			role1.setRoleName("Admin");
			role2.setRoleName("Buyer");
			role3.setRoleName("seller");
			role1.setUsers(Arrays.asList(user));
			role2.setUsers(Arrays.asList(user));
			role3.setUsers(Arrays.asList(user));
			List<Role> roles = Arrays.asList(role1,role2,role3);
			user.setRoles(new HashSet<>(roles));

		} else {
			List<Role> roles = roleRepository.findAllById(userDto.getRoleIds());
			user.setRoles(new HashSet<>(roles));
			roles.forEach(role -> {
				role.getUsers().add(user);
			});

		}
		userRepository.save(user);
		BeanUtils.copyProperties(user, userDto);

		return userDto;
	}

	@Override
	public UserUpdateDto updateUser(UserUpdateDto userUpdateDto) {
		User user = userRepository.findById(userUpdateDto.getUserId())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		BeanUtils.copyProperties(userUpdateDto, user);
		userRepository.save(user);
		BeanUtils.copyProperties(user, userUpdateDto);

		return userUpdateDto;
	}

}
