package com.neu.onlineBMP.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.onlineBMP.EXCEPTION.MessageException;
import com.neu.onlineBMP.POJO.Book;
import com.neu.onlineBMP.POJO.Message;
import com.neu.onlineBMP.POJO.Reply;
import com.neu.onlineBMP.POJO.User;

public class MessageDAO extends DAO {

	
	
	public MessageDAO() {
		
	}
	
	public List<Message> getMessages(User user) throws MessageException {
		
		try {
			begin();
			System.out.println("inside Message Dao retrieving");
//			long userID = user.getUserID(); 
//			Query q = getSession().createQuery("from User where userID = :userID");
//			
//			q.setString("userID", String.valueOf(userID));
//			User u = (User) q.uniqueResult();
////			user.setNotifications(0);
////			getSession().merge(user);
//			List<Message> messageList = u.getListOfMessages();
//			List<Message> sentMessages = u.getSentMessages();
			
System.out.println("inside message  gettinh message list message Dao");
			
			Query msgQuery = getSession().createQuery("from Message where sender =:userId or receiver=:userId");
			msgQuery.setString("userId", String.valueOf(user.getUserID()));
			
			List<Message> messageList = msgQuery.list();
			

			commit();
			return messageList;
		}
		catch(HibernateException e) {
			rollback();
			throw new MessageException("Exception while creating message: " + e.getMessage()); 
		}
		
		
	}
	
public boolean createMessages(User user, String receiverId, String bookId, Message message) throws MessageException {
		boolean flag = false;
		try {
			begin();
			System.out.println("inside Message inserting message Dao");
			
			
			
			message.setMsgRply("no");
			message.setSender(user);
			
			Query q = getSession().createQuery("from User where userID = :userID");
			q.setString("userID", receiverId);
			User receiver = (User) q.uniqueResult();
			
			Query q2 = getSession().createQuery("from User where userID = :senderID");
			q2.setString("senderID", String.valueOf(user.getUserID()));
			User sender = (User) q2.uniqueResult();
			
			System.out.println("*******Receiver id : "+receiverId+" ************");
			
			message.setReceiver(receiver);
			receiver.setNotifications(receiver.getNotifications() + 1);
			sender.getSentMessages().add(message);
			receiver.getListOfMessages().add(message);
			getSession().merge(receiver);
			getSession().merge(sender);
//			Iterator<Message> it = receiver.getListOfMessages().iterator();
			
//			while(it.hasNext()) {
//				System.out.println("*********");
//				System.out.println("Sender:"+it.next().getSender());
//				System.out.println("Message:"+it.next().getMessage());
//				System.out.println("*********");
//			}
			
			getSession().save(message);
			
			commit();
			flag=true;
			return flag;
			
		}
		catch(HibernateException e) {
			rollback();
			throw new MessageException("Exception while creating message: " + e.getMessage()); 
		}
		
		
	}













public void resetNotifications(User user) throws MessageException {
	
	try {
		
		begin();
		System.out.println("inside Message reseting notifications Dao");
		
		user.setNotifications(0);
		
		getSession().update(user);
	
		
//		Iterator<Message> it = receiver.getListOfMessages().iterator();
		
//		while(it.hasNext()) {
//			System.out.println("*********");
//			System.out.println("Sender:"+it.next().getSender());
//			System.out.println("Message:"+it.next().getMessage());
//			System.out.println("*********");
//		}
		
		
		
		commit();
		
	}
	catch(HibernateException e) {
		rollback();
		throw new MessageException("Exception while creating message: " + e.getMessage()); 
	}
	
	
}



	
}
