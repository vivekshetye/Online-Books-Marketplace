package com.neu.onlineBMP.POJO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.neu.onlineBMP.POJO.Book;



@Entity
@Table( name = "users" )
@Inheritance(strategy=InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userID", unique=true, nullable = false)
	public long userID;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "contactNo")
	private int contactNo;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	//private Inventory inventory;
	
	@Column(name = "notifications")
	private int notifications;
	
//	private List<ChatHistory> chatHistory = new ArrayList<ChatHistory>();
	
//	@Embedded
//	@ElementCollection
//	private Set<Book> listOfBooks = new HashSet();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Book> listOfBooks = new ArrayList<Book>();
	
	@OneToMany(mappedBy = "receiver")
	private List<Message> listOfMessages = new ArrayList<Message>();
	
	@OneToMany(mappedBy = "sender")
	private List<Message> sentMessages = new ArrayList<Message>();
	
	
	@OneToMany(mappedBy = "receiverRply")
	private List<Reply> replyReceived = new ArrayList<Reply>();
	
	@OneToMany(mappedBy="senderRply")
	private List<Reply> replySent = new ArrayList<Reply>();
	
	
 	
//	private Date dateOfBirth;
	
	
	
//	private FeedbackList feedbackList;

//	private double rating;
//	private String location;
	
	
	public User(String username, String password) {
		this.userName = username;
		this.password = password;
	}
	
	public User() {
		
	}
	
	
	@JsonIgnore
	public List<Reply> getReplyReceived() {
		return replyReceived;
	}

	public void setReplyReceived(List<Reply> replyReceived) {
		this.replyReceived = replyReceived;
	}

	@JsonIgnore
	public List<Reply> getReplySent() {
		return replySent;
	}

	public void setReplySent(List<Reply> replySent) {
		this.replySent = replySent;
	}

	@JsonIgnore
	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	@JsonIgnore
	public List<Message> getListOfMessages() {
		return listOfMessages;
	}

	public void setListOfMessages(List<Message> listOfMessages) {
		this.listOfMessages = listOfMessages;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
//	public Inventory getInventory() {
//		return inventory;
//	}
//	public void setInventory(Inventory inventory) {
//		this.inventory = inventory;
//	}
	
	
	public List<Book> getListOfBooks() {
		return listOfBooks;
	}
	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	
	
	
	public String getEmail() {
		return email;
	}
//	public Book getBook() {
//		return book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public Date getDateOfBirth() {
//		return dateOfBirth;
//	}
//	public void setDateOfBirth(Date dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}

	public long getUserID() {
		return userID;
	}

	public int getNotifications() {
		return notifications;
	}

	public void setNotifications(int notifications) {
		this.notifications = notifications;
	}
	
//	public String toString() {
//		return String.valueOf(userID);
//	}
	
	
//	public FeedbackList getFeedbackList() {
//		return feedbackList;
//	}
//	public void setFeedbackList(FeedbackList feedbackList) {
//		this.feedbackList = feedbackList;
//	}
	
	
	
	
//	public double getRating() {
//		return rating;
//	}
//	public void setRating(double rating) {
//		this.rating = rating;
//	}
//	public String getLocation() {
//		return location;
//	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
	
	
	
	
	
}
