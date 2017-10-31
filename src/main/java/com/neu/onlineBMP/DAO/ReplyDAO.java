package com.neu.onlineBMP.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.neu.onlineBMP.EXCEPTION.MessageException;
import com.neu.onlineBMP.EXCEPTION.ReplyException;
import com.neu.onlineBMP.POJO.Message;
import com.neu.onlineBMP.POJO.Reply;
import com.neu.onlineBMP.POJO.User;

public class ReplyDAO extends DAO {

	
	public ReplyDAO() {
		
	}
	
	
	public void createReplyMsg(User user, String receiverId, Reply reply, String msgThreadId) throws ReplyException {
		
		try {
			begin();
			System.out.println("inside reply Message inserting reply Dao");
			
			Query msgQuery = getSession().createQuery("from Message where mesageId =:mesageId");
			msgQuery.setString("mesageId", msgThreadId);
			Message message = (Message) msgQuery.uniqueResult();
			
			reply.setMessage(message);
			
			Query q = getSession().createQuery("from User where userID = :userID");
			q.setString("userID", receiverId);
			User receiver = (User) q.uniqueResult();
			reply.setReceiverRply(receiver);
			
			//List<Message> tempReceiverMsgList = receiver.getListOfMessages();
			
			receiver.getReplyReceived().add(reply);
			
			
			Query q2 = getSession().createQuery("from User where userID = :senderID");
			q2.setString("senderID", String.valueOf(user.getUserID()));
			User sender = (User) q2.uniqueResult();
			
			System.out.println("*******Receiver id : "+receiverId+" ************");
			
			sender.getReplySent().add(reply);
			
			
			//receiver.setNotifications(receiver.getNotifications() + 1);
			
			getSession().merge(receiver);
			getSession().merge(sender);

			
			getSession().save(reply);
			
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
			throw new ReplyException("Exception while creating message: " + e.getMessage()); 
		}
		
		
	}
	
	
	
public List<Reply> getReplies(User user) throws ReplyException {
		
		try {
			begin();
			System.out.println("inside reply  gettinh replies list reply Dao");
			
			Query msgQuery = getSession().createQuery("from Reply where senderId =:userId or receiverId=:userId");
			msgQuery.setString("userId", String.valueOf(user.getUserID()));
			
			List<Reply> repliesList = msgQuery.list();
			
			
//			Criteria cr = getSession().createCriteria(Reply.class);
//
//			Criterion senderId = Restrictions.eq("senderRply", user.getUserID());
//			Criterion receiverId = Restrictions.eq("receiverRply",user.getUserID());
//
//			// 
//			LogicalExpression orExp = Restrictions.or(senderId,receiverId);
//			cr.add( orExp );
			

			
			commit();
			
			return repliesList;
		}
		catch(HibernateException e) {
			rollback();
			throw new ReplyException("Exception while retrieving reply: " + e.getMessage()); 
		}
		
		
	}
	
	
	
}
