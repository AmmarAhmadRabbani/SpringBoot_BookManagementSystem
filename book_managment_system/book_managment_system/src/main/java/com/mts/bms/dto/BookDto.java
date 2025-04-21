package com.mts.bms.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
	private Long bookId;
	private String title;
	private String decription;
	private String author;
	private String edition;
	private Double price;
	private MultipartFile image;
	private Long categoryId;

}
