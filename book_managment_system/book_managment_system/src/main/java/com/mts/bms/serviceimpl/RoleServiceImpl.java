package com.mts.bms.serviceimpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mts.bms.dto.RoleDto;
import com.mts.bms.entity.Role;
import com.mts.bms.exception.RoleNotFoundException;
import com.mts.bms.repository.RoleRepository;
import com.mts.bms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDto addRole(RoleDto roleDto) {
		Optional<Role> roleId = roleRepository.findById(roleDto.getRoleId());
		if (!roleId.isPresent()) {
			Role role = new Role();
			BeanUtils.copyProperties(roleDto, role);
			roleRepository.save(role);
			BeanUtils.copyProperties(role, roleDto);
			return roleDto;
		} else {

			throw new RoleNotFoundException("Role alredy present");

		}
	}

}
