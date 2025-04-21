package com.mts.bms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
	private Long bookId;
	private String title;
	private String decription;
	private String author;
	private String edition;
	private Double price;
	private String image;
	private CategoryResponseDto category;

}
