package com.mts.bms.service;

import com.mts.bms.dto.UserDto;
import com.mts.bms.dto.UserUpdateDto;

public interface UserService {
	UserDto userRegister(UserDto userDto);

	UserUpdateDto updateUser(UserUpdateDto userUpdateDto);

}
