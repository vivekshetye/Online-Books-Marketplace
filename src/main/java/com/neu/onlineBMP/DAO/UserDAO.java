package com.neu.onlineBMP.DAO;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;


import com.neu.onlineBMP.EXCEPTION.UserException;
import com.neu.onlineBMP.POJO.User;
import com.neu.onlineBMP.POJO.Book;



public class UserDAO extends DAO{

	
	public UserDAO() {
	}
	
	public User get(String userName, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :userName and password = :password");
			q.setString("userName", userName);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userName, e);
		}
	}
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID= :userID");
			q.setInteger("userID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			
			User user = new User(u.getUserName(), u.getPassword());

			//user.setListOfBooks(bookList);
			user.setEmail(u.getEmail());
			
			getSession().save(user);
			commit();
			close();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public int getNotifications(long userId) throws UserException {
		try {
			begin();
			
			
			Query q = getSession().createQuery("from User where userID= :userID");
			q.setLong("userID", userId);		
			User user = (User) q.uniqueResult();
			int notification = user.getNotifications();
			commit();
			return notification;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	
//	public User addBooks(User u, Set<Book> bookList)
//			throws UserException {
//		try {
//			begin();
//			System.out.println("inside book DAO");
//
//			
//			//User user = new User(u.getUserName(), u.getPassword());
//
//			for(Book b : bookList) {
//				b.setUser(u);
//			}
//			
//			u.setListOfBooks(bookList);
//			///user.setEmail(u.getEmail());
//			
//			getSession().update(u);
//			commit();
//			return u;
//
//		} catch (HibernateException e) {
//			rollback();
//			throw new UserException("Exception while creating user: " + e.getMessage());
//		}
//	}
	
	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUserName(), e);
		}
	}
	
	public boolean checkUserExists(User user) throws UserException {
		boolean flag = false;
		try {
			begin();
			
			Query q = getSession().createQuery("from User where userName =:userName ");
			q.setString("userName",user.getUserName());
			User u = (User) q.uniqueResult();
			
			if(u != null) {
				flag = true;
			}
			
			
			commit();
			
			return flag;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUserName(), e);
		}
	}
	
	
	
	
}
