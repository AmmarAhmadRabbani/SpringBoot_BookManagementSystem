package com.mts.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mts.bms.dto.UserDto;
import com.mts.bms.dto.UserUpdateDto;
import com.mts.bms.response.SuccessResponse;
import com.mts.bms.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> userRegister(@RequestBody UserDto userDto) {
		UserDto userRegister = userService.userRegister(userDto);
		if (userRegister != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Added Successfully", userRegister), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Not Added", null), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<SuccessResponse> updateUser(@PathVariable UserUpdateDto userUpdateDto) {
		UserUpdateDto updateUser = userService.updateUser(userUpdateDto);
		if (updateUser != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Updated", updateUser), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Not Updated", null), HttpStatus.BAD_REQUEST);
		}
	}

}
