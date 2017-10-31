package com.neu.onlineBMP.POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reply")
public class Reply {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "replyID", unique=true, nullable = false)
	private long replyId;
	
	@OneToOne
	private Message message;
	
	@Column(name="Reply_Message")
	private String replyMessage;
	
	@ManyToOne
	@JoinColumn(name="senderId")
	private User senderRply;
	
	@ManyToOne
	@JoinColumn(name="receiverId")
	private User receiverRply;
	
	
	
	public Reply() {
		
	}



	public String getReplyMessage() {
		return replyMessage;
	}



	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}



	



	public User getSenderRply() {
		return senderRply;
	}



	public void setSenderRply(User senderRply) {
		this.senderRply = senderRply;
	}



	public User getReceiverRply() {
		return receiverRply;
	}



	public void setReceiverRply(User receiverRply) {
		this.receiverRply = receiverRply;
	}



	public long getReplyId() {
		return replyId;
	}



	public Message getMessage() {
		return message;
	}



	public void setMessage(Message message) {
		this.message = message;
	}

	
	
	
}
