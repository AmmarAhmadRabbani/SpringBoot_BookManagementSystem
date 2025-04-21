package com.mts.bms.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	@Column(name = "email_id", unique = true, nullable = false, length = 50)
	private String emailId;
	@Column(name = "mobile_number", unique = true, nullable = false, precision = 15)
	private Long phoneNumber;
	@Column(name = "password", nullable = false, length = 200)
	private String password;
	@Column(name = "age", nullable = false, precision = 3)
	private Integer age;
	@Column(name = "dob", nullable = false)
	@JsonFormat(pattern = "yyyy-mm-dd", shape = Shape.STRING)
	private LocalDate dob;
	@Column(name = "gender", length = 15)
	private String gender;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "users", fetch = FetchType.EAGER)
	private Set<Role> roles;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_books", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;
}
