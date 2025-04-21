package com.mts.bms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mts.bms.entity.Book;

@Repository

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitle(String title);

	List<Book> findByAuthor(String author);

	@Query("SELECT u FROM Book u WHERE u.title LIKE %?1% OR u.author LIKE %?1%")
	List<Book> searchBookBasedOnTitleOrAuthor(String search);

	List<Book> findByPriceGreaterThanEqualAndPriceLessThanEqual(Double minPrice, Double maxPrice);

}
