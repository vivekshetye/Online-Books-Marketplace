package com.neu.onlineBMP.CONTROLLER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.onlineBMP.DAO.BookDAO;
import com.neu.onlineBMP.DAO.MessageDAO;
import com.neu.onlineBMP.DAO.ReplyDAO;
import com.neu.onlineBMP.DAO.UserDAO;
import com.neu.onlineBMP.POJO.Message;
import com.neu.onlineBMP.POJO.Reply;
import com.neu.onlineBMP.POJO.User;

@Controller
public class ReplyController {

	public ReplyController() {
		
	}
	
	
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
	
	
	@RequestMapping(value = "user/createReply", method = RequestMethod.POST)
	protected ModelAndView createReply(HttpServletRequest request,@ModelAttribute("reply") Reply reply, BindingResult result) throws Exception {
		System.out.print(" **********inside reply controller**********");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		ModelAndView mv = new ModelAndView();
		//String receiverId = String.valueOf(session.getAttribute("receiverId"));
		//String bookId = String.valueOf(session.getAttribute("bookId"));
		
		String receiverId = request.getParameter("senderId");
		
		
		
		
		reply.setSenderRply(user);
		
		String msgThreadId = request.getParameter("messageId");
		
		replyDao.createReplyMsg(user, receiverId, reply, msgThreadId);
		
		
		
		return new ModelAndView("user-home");

	}
	
}
