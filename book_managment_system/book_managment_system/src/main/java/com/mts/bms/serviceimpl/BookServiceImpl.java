package com.mts.bms.serviceimpl;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mts.bms.dto.BookDto;
import com.mts.bms.dto.BookResponseDto;
import com.mts.bms.dto.CategoryResponseDto;
import com.mts.bms.entity.Book;
import com.mts.bms.entity.Category;
import com.mts.bms.exception.BookNotFoundException;
import com.mts.bms.exception.CategoryNotFoundException;
import com.mts.bms.repository.BookRepository;
import com.mts.bms.repository.CategoryRepository;
import com.mts.bms.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Value("${file.upload.location}")
	private String dir;

	private Path getPath(String fileName) {
		return Paths.get(this.dir + "\\" + fileName).toAbsolutePath().normalize();
	}

	private String addFile(String folderName, MultipartFile multipartFile) {
		try {
			Path dirLocation = getPath(folderName);
			if (multipartFile != null) {
				Files.createDirectories(dirLocation);
				String filename = multipartFile.getOriginalFilename();
				Path filePath = dirLocation.resolve(filename);
				Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				return filePath.toString();
			} else {
				throw new FileNotFoundException("file Not Found");
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public BookResponseDto addBook(BookDto bookDto) {

		Optional<Book> dbId = bookRepository.findById(bookDto.getBookId());
		BookResponseDto bookResponseDto = new BookResponseDto();
		if (!dbId.isPresent()) {
			Book book = new Book();
			BeanUtils.copyProperties(bookDto, book);

			Category category = categoryRepository.findById(bookDto.getCategoryId())
					.orElseThrow(() -> new CategoryNotFoundException("Category Not Present"));

			book.setCategory(category);
			book.setImage(addFile("Book Image", bookDto.getImage()));

			bookRepository.save(book);
			BeanUtils.copyProperties(book, bookResponseDto);
			return bookResponseDto;

		} else {
			throw new BookNotFoundException("book already present");
		}

	}

	@Override
	public List<BookResponseDto> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookResponseDto> bookResponseDtos = new ArrayList<>();
		if (books != null) {
			books.forEach(book -> {
				BookResponseDto bookResponseDto = new BookResponseDto();
				BeanUtils.copyProperties(book, bookResponseDto);
				Category category = book.getCategory();
				CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
				BeanUtils.copyProperties(category, categoryResponseDto);
				bookResponseDto.setCategory(categoryResponseDto);
				bookResponseDtos.add(bookResponseDto);
			});
			return bookResponseDtos;
		} else {
			throw new BookNotFoundException("Books unavaliable");
		}
	}

	@Override
	public BookResponseDto updateBook(BookDto bookDto) {
		Book book = bookRepository.findById(bookDto.getBookId())
				.orElseThrow(() -> new BookNotFoundException("Book not present"));

		BeanUtils.copyProperties(bookDto, book);
		Category category = categoryRepository.findById(bookDto.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException("Category not present"));
		book.setCategory(category);
		bookRepository.save(book);
		BookResponseDto bookResponseDto = new BookResponseDto();
		BeanUtils.copyProperties(book, bookResponseDto);
		return bookResponseDto;
	}

	@Override
	public BookResponseDto getBookById(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not present"));
		BookResponseDto bookResponseDto = new BookResponseDto();
		BeanUtils.copyProperties(book, bookResponseDto);
		Category category = book.getCategory();
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		BeanUtils.copyProperties(category, categoryResponseDto);
		bookResponseDto.setCategory(categoryResponseDto);
		return bookResponseDto;
	}

	@Override
	public Boolean deleteBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));
		bookRepository.delete(book);
		return true;
	}
	

	@Override
	public List<BookResponseDto> filterBasedOnPrice(Double minPrice, Double maxPrice) {
		List<Book> books = bookRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
		List<BookResponseDto> bookResponseDtos = new ArrayList<>();
		if (books != null) {
			books.forEach(book -> {
				BookResponseDto bookResponseDto = new BookResponseDto();
				BeanUtils.copyProperties(book, bookResponseDto);
				bookResponseDtos.add(bookResponseDto);

			});
			return bookResponseDtos;
		} else {
			throw new BookNotFoundException("Book not available ");
		}

	}

	@Override
	public List<BookResponseDto> getBookByAuthor(String author) {
		List<Book> dbAuthor = bookRepository.findByAuthor(author);
		List<BookResponseDto> bookResponseDtos = new ArrayList<>();
		if (dbAuthor != null) {
			dbAuthor.forEach(authorName -> {
				BookResponseDto bookResponseDto = new BookResponseDto();
				BeanUtils.copyProperties(authorName, bookResponseDto);
				CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
				BeanUtils.copyProperties(authorName.getCategory(), categoryResponseDto);
				bookResponseDto.setCategory(categoryResponseDto);
				bookResponseDtos.add(bookResponseDto);

			});
			return bookResponseDtos;
		} else {
			throw new BookNotFoundException("Author not present");
		}

	}

	@Override
	public List<BookResponseDto> bookSearch(String search) {
		List<Book> books = bookRepository.searchBookBasedOnTitleOrAuthor(search);
		List<BookResponseDto> bookResponseDtos = new ArrayList<>();
		if (books != null) {
			books.forEach(book -> {
				BookResponseDto bookResponsesDto = new BookResponseDto();
				BeanUtils.copyProperties(book, bookResponsesDto);
				Category category = book.getCategory();
				CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
				BeanUtils.copyProperties(category, categoryResponseDto);
				bookResponsesDto.setCategory(categoryResponseDto);
				bookResponseDtos.add(bookResponsesDto);

			});

			return bookResponseDtos;
		} else {
			throw new BookNotFoundException("Book unavailable");

		}
	}

}
