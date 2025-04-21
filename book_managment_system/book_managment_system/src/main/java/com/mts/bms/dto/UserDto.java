package com.mts.bms.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long userId;
	private String name;
	private String emailId;
	private Long phoneNumber;
	private String password;
	private Integer age;
	private LocalDate dob;
	private String gender;
	private List<Long> roleIds;

}
