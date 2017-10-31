package com.neu.onlineBMP.POJO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Message")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "messageID", unique=true, nullable = false)
	private int mesageId;
	
	@Column(name="message")
	private String message;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="senderId")
	private User sender;
	
	@Column(name="Reply_Status")
	private String msgRply;
	
	@ManyToOne
	@JoinColumn(name="receiverId")
	private User receiver;
	
	
	
	@OneToMany(mappedBy="receiver", fetch = FetchType.EAGER)
	private List<Message> replies = new ArrayList<Message>();
	
	public Message() {
		
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getMsgRply() {
		return msgRply;
	}

	public void setMsgRply(String msgRply) {
		this.msgRply = msgRply;
	}

	public List<Message> getReplies() {
		return replies;
	}

	public void setReplies(List<Message> replies) {
		this.replies = replies;
	}

	

	public int getMesageId() {
		return mesageId;
	}
	
	
	
}
