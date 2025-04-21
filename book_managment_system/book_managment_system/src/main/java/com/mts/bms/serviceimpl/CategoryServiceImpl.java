package com.mts.bms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mts.bms.dto.BookDto;
import com.mts.bms.dto.CategoryDto;
import com.mts.bms.dto.CategoryResponseDto;
import com.mts.bms.entity.Book;
import com.mts.bms.entity.Category;
import com.mts.bms.exception.CategoryNotFoundException;
import com.mts.bms.repository.CategoryRepository;
import com.mts.bms.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {

		Optional<Category> findByCategoryName = categoryRepository
				.findByCategoryNameIgnoreCase(categoryDto.getCategoryName());
		if (!findByCategoryName.isPresent()) {
			Category category = new Category();

			BeanUtils.copyProperties(categoryDto, category);
			categoryRepository.save(category);
			CategoryDto categoryDtos = new CategoryDto();
			BeanUtils.copyProperties(category, categoryDtos);
			return categoryDtos;
		} else {
			throw new CategoryNotFoundException("Not added");
		}

	}

	@Override
	public String deleteCategory(Long categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Id Not Found"));

		categoryRepository.delete(category);
		return "Deleted Successfully";

	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		if (!categories.isEmpty()) {
			categories.forEach(category -> {
				CategoryDto categoryDto = new CategoryDto();
				BeanUtils.copyProperties(category, categoryDto);
				categoryDtos.add(categoryDto);
			});

		}

		return categoryDtos;
	}

	@Override
	public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("Id not found"));
		BeanUtils.copyProperties(categoryDto, category);
		categoryRepository.save(category);
		CategoryDto dto = new CategoryDto();
		BeanUtils.copyProperties(category, dto);
		return dto;
	}

	@Override
	public List<CategoryResponseDto> filterBasedOnCategory(String categoryName) {
		Category category = categoryRepository.findByCategoryNameIgnoreCase(categoryName).get();
		List<CategoryResponseDto> categoryResponseDto = new ArrayList<>();
		if (category != null) {
			CategoryResponseDto responseDto = new CategoryResponseDto();
			BeanUtils.copyProperties(category, responseDto);
			List<Book> books = category.getBooks();
			List<BookDto> bookDtos = new ArrayList<>();
			books.forEach(book -> {
				BookDto bookDto = new BookDto();
				BeanUtils.copyProperties(book, bookDto);
				bookDtos.add(bookDto);
			});
			responseDto.setBookDto(bookDtos);
			categoryResponseDto.add(responseDto);

		}

		return categoryResponseDto;
	}
}
