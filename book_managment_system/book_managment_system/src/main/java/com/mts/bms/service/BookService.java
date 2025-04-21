package com.mts.bms.service;

import java.util.List;

import com.mts.bms.dto.BookDto;
import com.mts.bms.dto.BookResponseDto;

public interface BookService {

	public BookResponseDto addBook(BookDto bookDto);

	List<BookResponseDto> getAllBooks();

	BookResponseDto updateBook(BookDto bookDto);

	BookResponseDto getBookById(Long bookId);

	Boolean deleteBook(Long bookId);

	List<BookResponseDto> filterBasedOnPrice(Double minPrice, Double maxPrice);

	List<BookResponseDto> getBookByAuthor(String author);

	List<BookResponseDto> bookSearch(String search);
}
