package com.mts.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mts.bms.dto.CategoryDto;
import com.mts.bms.dto.CategoryResponseDto;
import com.mts.bms.response.SuccessResponse;
import com.mts.bms.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/addCategory")
	public ResponseEntity<SuccessResponse> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto addCategory = categoryService.addCategory(categoryDto);
		if (addCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Category Added Successfully", addCategory),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "Category Already present", null),
				HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<SuccessResponse> deleteCategory(@PathVariable Long categoryId) {
		String deleteCategory = categoryService.deleteCategory(categoryId);
		if (deleteCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Deleted", null), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Not Deletd", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllCategories")
	public ResponseEntity<SuccessResponse> getAllCategories() {
		List<CategoryDto> allCategories = categoryService.getAllCategories();
		if (allCategories != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Successfull", allCategories), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Getting failed", null), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<SuccessResponse> updateCategory(@PathVariable Long categoryId,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto updateCategory = categoryService.updateCategory(categoryId, categoryDto);
		if (updateCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Updated", updateCategory), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Updation Failed", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("filter/{categoryName}")
	public ResponseEntity<SuccessResponse> filterBasedOnCategory(@PathVariable String categoryName) {
		List<CategoryResponseDto> filterCategory = categoryService.filterBasedOnCategory(categoryName);
		if (filterCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Filtered", filterCategory), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Filteration failed", null), HttpStatus.BAD_REQUEST);

		}
	}

}
