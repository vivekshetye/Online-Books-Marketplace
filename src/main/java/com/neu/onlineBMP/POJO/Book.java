package com.neu.onlineBMP.POJO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="Book")
//@PrimaryKeyJoinColumn(name = "userID")
public class Book{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bookID", unique=true, nullable = false)
//	@GeneratedValue(generator = "generator")
//	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
//	@Column(name = "bookID", unique = true, nullable = false)
	private int bookId;
	
//	@ManyToOne
////	(targetEntity = User.class)
//	@JoinColumn(name = "userId")
//	private long userId;
	
	
	
	@ManyToOne
	@JoinColumn(name = "userID")
	@JsonIgnore
	private User user;
	
	@Column(name = "bookName")
	private String bookName;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name= "author")
	private String author;
	
	@Column(name = "filename")
	private String filename;
	
	@Transient
	private CommonsMultipartFile photo;
	
//	private boolean status;
//	private double price;
//	private String location;
//	private Date datePosted;
	
	public Book() {
		
	}
	
	
//	public int getBookId() {
//		return bookId;
//	}
	
	@JsonIgnore
	@JsonBackReference
	public User getUser() {
		return user;
	}


	
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getBookName() {
		return bookName;
	}
//	public long getUserId() {
//		return userId;
//	}
//
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	

	public CommonsMultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}


	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
//	public boolean isStatus() {
//		return status;
//	}
//	public void setStatus(boolean status) {
//		this.status = status;
//	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	public String getLocation() {
//		return location;
//	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
//	public Date getDatePosted() {
//		return datePosted;
//	}
//	public void setDatePosted(Date datePosted) {
//		this.datePosted = datePosted;
//	}


	public int getBookId() {
		return bookId;
	}
	
	
	
	
	
}
