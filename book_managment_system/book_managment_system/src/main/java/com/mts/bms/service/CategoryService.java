package com.mts.bms.service;

import java.util.List;

import com.mts.bms.dto.CategoryDto;
import com.mts.bms.dto.CategoryResponseDto;

public interface CategoryService {

	public CategoryDto addCategory(CategoryDto categoryDto);

	public String deleteCategory(Long categoryId);

	List<CategoryDto> getAllCategories();

	CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

	List<CategoryResponseDto> filterBasedOnCategory(String categoryName);

}
