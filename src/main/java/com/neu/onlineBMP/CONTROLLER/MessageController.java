package com.neu.onlineBMP.CONTROLLER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.onlineBMP.DAO.BookDAO;
import com.neu.onlineBMP.DAO.MessageDAO;
import com.neu.onlineBMP.DAO.ReplyDAO;
import com.neu.onlineBMP.DAO.UserDAO;
import com.neu.onlineBMP.POJO.Book;
import com.neu.onlineBMP.POJO.Message;
import com.neu.onlineBMP.POJO.Reply;
import com.neu.onlineBMP.POJO.User;

@Controller
public class MessageController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("messageDao")
	MessageDAO messageDao;
	
	@Autowired
	@Qualifier("bookDao")
	BookDAO bookDao;
	
	@Autowired
	@Qualifier("replyDao")
	ReplyDAO replyDao;
	
	
	
	@RequestMapping(value = "user/getMessages", method = RequestMethod.GET)
	protected ModelAndView getMessages(HttpServletRequest request) throws Exception {
		System.out.print("Retrieve messges");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		List<Book> bookList = bookDao.getMyBooks(user);
		
	
		
		
		
		List<Message> messageList = messageDao.getMessages(user);
		
		List<Reply> replyList = replyDao.getReplies(user);
		
		 
		ModelAndView mv = new ModelAndView();
		
		
		mv.addObject(messageList);
		mv.addObject(replyList);
		
		mv.addObject("reply",new Reply());
		mv.setViewName("view-messages");
		return mv;

	}
	
	

	
	
	
	@RequestMapping(value = "user/sendMessage", method = RequestMethod.GET)
	protected ModelAndView getMessages(HttpServletRequest request, @RequestParam(value="id") long bookId) throws Exception {
		System.out.print("Creating messges");
		
		long bId = bookId;
		long receiverId = bookDao.getUserId(bId);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("receiverId", receiverId);
		session.setAttribute("bookId", bId);
		
		Book book = bookDao.getBookById(bId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create-message");
		mv.addObject("message", new Message());
		mv.addObject("book", book);

		return mv;

	}
	
	
	@RequestMapping(value = "user/createMessage", method = RequestMethod.POST)
	protected ModelAndView createMessage(HttpServletRequest request,@ModelAttribute("message") Message message, BindingResult result) throws Exception {
		System.out.print("Sending messges");
		boolean flag = false;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		ModelAndView mv = new ModelAndView();
		String receiverId = String.valueOf(session.getAttribute("receiverId"));
		String bookId = String.valueOf(session.getAttribute("bookId"));
		long bId = (Long) session.getAttribute("bookId");
		Book book = bookDao.getBookById(bId);
		
//		Book book = bookDao.getBookById(Long.parseLong(bookId));
		message.setBook(book);
		flag = messageDao.createMessages(user, receiverId, bookId, message);
		
		
		
		request.setAttribute("book", book);
		
		session.removeAttribute("receiverId");
		session.removeAttribute("bookId");
		
		return new ModelAndView("create-message","messageFlag",flag);

	}
	

	
	@RequestMapping(value = "user/getNotifications", method = RequestMethod.GET)
	@ResponseBody
	protected int getNotifications(HttpServletRequest request) throws Exception {
		System.out.print(" getting notifications");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		int notification = userDao.getNotifications(user.getUserID());
		
		
		return notification;

	}
	

	
}
