package com.mts.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mts.bms.dto.BookDto;
import com.mts.bms.dto.BookResponseDto;
import com.mts.bms.response.SuccessResponse;
import com.mts.bms.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping("/addBook")
	public ResponseEntity<SuccessResponse> addBook(@ModelAttribute BookDto bookDto) {

		BookResponseDto addBook = bookService.addBook(bookDto);
		if (addBook != null) {

			return new ResponseEntity<>(new SuccessResponse(false, "Book Added Successfully ", addBook), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "Book Already Present", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<SuccessResponse> getAllBooks() {
		List<BookResponseDto> allBooks = bookService.getAllBooks();
		if (allBooks != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Getting successfully", allBooks), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Getting failed", null), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/updateBook")
	public ResponseEntity<SuccessResponse> updateBook(@ModelAttribute BookDto bookDto) {
		BookResponseDto updateBook = bookService.updateBook(bookDto);
		if (updateBook != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Updated", updateBook), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Updation Failed", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getBook/{bookId}")
	public ResponseEntity<SuccessResponse> getBookById(@PathVariable Long bookId) {
		BookResponseDto bookById = bookService.getBookById(bookId);
		if (bookById != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Getting Successfull", bookId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Getting failed", null), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<SuccessResponse> deleteBook(@PathVariable Long bookId) {
		Boolean deleteBook = bookService.deleteBook(bookId);
		if (deleteBook != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Deleted", deleteBook), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Not Deleted", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/filterBook/{minPrice}/{maxPrice}")
	public ResponseEntity<SuccessResponse> filterBasedOnPrice(@PathVariable Double minPrice,
			@PathVariable Double maxPrice) {
		List<BookResponseDto> filterBasedOnPrice = bookService.filterBasedOnPrice(minPrice, maxPrice);
		if (filterBasedOnPrice != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Filtered", filterBasedOnPrice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Filtering failed", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/bookSearch/{search}")
	public ResponseEntity<SuccessResponse> bookSearch(@PathVariable String search) {
		List<BookResponseDto> bookSearch = bookService.bookSearch(search);
		if (bookSearch != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Successfull", bookSearch), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "Search failed", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getBooks/{author}")
	public ResponseEntity<SuccessResponse> getBookByAuthor(@PathVariable String author) {
		List<BookResponseDto> bookByAuthor = bookService.getBookByAuthor(author);
		if (bookByAuthor != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Successful", bookByAuthor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(true, "failed", null), HttpStatus.BAD_REQUEST);
		}

	}

}
