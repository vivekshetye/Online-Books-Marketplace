package com.neu.onlineBMP.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.onlineBMP.EXCEPTION.BookException;
import com.neu.onlineBMP.EXCEPTION.MessageException;
import com.neu.onlineBMP.EXCEPTION.UserException;
import com.neu.onlineBMP.POJO.Book;
import com.neu.onlineBMP.POJO.Message;
import com.neu.onlineBMP.POJO.User;

public class BookDAO extends DAO {

	public BookDAO() {
	}
	
	public void addBook(Book b, User u) throws BookException {
		try{
			
		begin();
		System.out.println("inside BOOKDAO");
		b.setUser(u);
		u.getListOfBooks().add(b);
		getSession().merge(u);
		System.out.println(u.getUserName() +"" +u.getUserID());
		getSession().save(b);
		commit();
		}
		catch(HibernateException e) {
			rollback();
			throw new BookException("Exception while creating book: " + e.getMessage());
		}
		
	}
	
	public Book get(String bookName) throws BookException {
		try {
			begin();
			Query q = getSession().createQuery("from Book where bookName = :bookName");
			q.setString("bookName", bookName);
					
			Book book = (Book) q.uniqueResult();
			commit();
			return book;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get book " + bookName, e);
		}
	}
	
	public Book getBookById(long bookId) throws BookException {
		try {
			begin();
			Query q = getSession().createQuery("from Book where bookId = :bookId");
			q.setString("bookId", String.valueOf(bookId));
					
			Book book = (Book) q.uniqueResult();
			commit();
			return book;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get book " + bookId, e);
		}
	}
	
	public List<Book> getBookList(long userID) throws BookException {
		try {
			begin();
			Query q = getSession().createQuery("from Book where userID != :userID");
			q.setString("userID", String.valueOf(userID));
			List<Book> bookList = q.list();
			commit();
			return bookList;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get books " + e);
		}
	}
	
	public void deleteBook(long bookId, User user) throws BookException {
		try {
			begin();
			Query q = getSession().createQuery("from Book where bookId = :bookId");
			q.setString("bookId", String.valueOf(bookId));
			Book book = (Book) q.uniqueResult();
			
			Query q1 = getSession().createQuery("from User where userID =:userID");
			q1.setString("userID", String.valueOf(user.getUserID()));
			User u = (User) q1.uniqueResult();
			
			u.getListOfBooks().remove(book);
			
			getSession().merge(u);
			
			getSession().delete(book);
			
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get books " + e);
		}
	}
	
	public List<Book> getAllBookList() throws BookException {
		try {
			begin();
			Query q = getSession().createQuery("from Book");
			
			List<Book> bookList = q.list();
			commit();
			return bookList;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get books " + e);
		}
	}
	
	
	public List<Book> getBookSearchList( String bookName, User user) throws BookException {
		try {
			begin();
			
			String userID = String.valueOf(user.getUserID());
			Query q = getSession().createQuery("from Book where bookName=:bookName and userID != :userID");
			q.setString("bookName", bookName);
			q.setString("userID", userID);
			List<Book> bookList = q.list();
			commit();
			return bookList;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get books " + e);
		}
	}
	
	
	public List<Book> getAllBookSearchList( User user) throws BookException {
		try {
			begin();
			
			String userID = String.valueOf(user.getUserID());
			Query q = getSession().createQuery("from Book where userID != :userID");
			
			q.setString("userID", userID);
			List<Book> bookList = q.list();
			commit();
			return bookList;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get books " + e);
		}
	}
	
	
	
	
	public List<Book> getMyShelfList(User user) throws BookException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID=:userID");
			q.setString("userID", String.valueOf(user.getUserID()));
			User u = (User) q.uniqueResult();
			
			List<Book> bookList = u.getListOfBooks();
			
			commit();
			return bookList;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get books " + e);
		}
	}
	
	
	
	public long getUserId(long bookId) throws BookException {
		
		try {
			begin();
			Query q = getSession().createQuery("from Book where bookId = :bookId");
			q.setString("bookId", String.valueOf(bookId));
					
			Book book = (Book) q.uniqueResult();
			User user = (User) book.getUser();
			long userId = user.getUserID();
			commit();
			return userId;
		} catch (HibernateException e) {
			rollback();
			throw new BookException("Could not get book " + bookId, e);
		}
		
		
		
	}
	
public List<Book> getMyBooks(User user) throws MessageException {
		
		try {
			begin();
			System.out.println("inside Book Dao retrieving");
//			long userID = user.getUserID(); 
//			Query q = getSession().createQuery("from User where userID = :userID");
//			
//			q.setString("userID", String.valueOf(userID));
//			User u = (User) q.uniqueResult();
			List<Book> bookList = user.getListOfBooks();
			List<Message> msgSet;
			msgSet = user.getListOfMessages();
//			Iterator<Book> it = user.getListOfBooks().iterator();
//			
//			while(it.hasNext()) {
//				bookList.add(it.next());
//			}
			commit();
			return bookList;
		}
		catch(HibernateException e) {
			rollback();
			throw new MessageException("Exception while creating message: " + e.getMessage()); 
		}
		
		
	}
}
