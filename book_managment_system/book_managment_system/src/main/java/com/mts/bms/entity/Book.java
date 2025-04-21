package com.mts.bms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	@Column(name = "book_title", nullable = false, length = 20)
	private String title;
	@Column(name = "book_decription", nullable = false, length = 150)
	private String decription;
	@Column(name = "author_name", nullable = false, length = 45)
	private String author;
	@Column(name = "book_edition", nullable = false, length = 20)
	private String edition;
	@Column(name = "book_price", nullable = false, precision = 4)
	private Double price;
	@Column(name="book_image")
	private String image;
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "books")
	private List<User> users;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
	private Category category;

}
