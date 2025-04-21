package com.mts.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mts.bms.dto.RoleDto;
import com.mts.bms.response.SuccessResponse;
import com.mts.bms.service.RoleService;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping("/addRole")
	public ResponseEntity<SuccessResponse> addRole(@RequestBody RoleDto roleDto) {
		RoleDto addRole = roleService.addRole(roleDto);
		if (addRole != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Added Successfully", addRole), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Not added", null), HttpStatus.BAD_REQUEST);
		}

	}

}
