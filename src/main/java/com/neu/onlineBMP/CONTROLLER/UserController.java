package com.neu.onlineBMP.CONTROLLER;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.onlineBMP.DAO.BookDAO;
import com.neu.onlineBMP.DAO.UserDAO;
import com.neu.onlineBMP.EXCEPTION.UserException;
import com.neu.onlineBMP.POJO.Book;
import com.neu.onlineBMP.POJO.User;
import com.neu.onlineBMP.VALIDATOR.UserValidator;



@Controller
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("bookDao")
	BookDAO bookDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected ModelAndView goToUserHome(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		request.getSession().invalidate();
		List<Book> bookList = bookDao.getAllBookList();	
		mv.addObject(bookList);
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	protected ModelAndView goLoginPage(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("login-page");
		return mv;
	}
	

	
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-form", "user", new User());

	}
	
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	protected ModelAndView logout() throws Exception {
		System.out.print("logout");

		return new ModelAndView("home");

	}
	
	
	@RequestMapping(value = "/user/createUser", method = RequestMethod.POST)
	protected ModelAndView addNewMovie(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		//validator.validate(user, result);
		boolean duplicateUserFlag = false;
		
		if (result.hasErrors()) {
			return new ModelAndView("register-form", "user", user);
		}
		
		
		try {

			System.out.print("registerNewUser");

			
			duplicateUserFlag = userDao.checkUserExists(user);
			
			if(duplicateUserFlag) {
				
				return new ModelAndView("register-form", "duplicateUserFlag", duplicateUserFlag);
				
				
				
			}
			
			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			List<Book> bookList = bookDao.getBookList(u.getUserID());
			User userSes = (User) request.getSession().getAttribute("user");
//			userDao.addBooks(userSes, bookList);
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("user-home");
			mv.addObject("user", u);
			
			if(bookList.size() > 0) {
				mv.addObject("bookList", bookList);
			}
			
			return mv;

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {
			ModelAndView mv = new ModelAndView();
			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("userName"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				mv.setViewName("error");
				return mv;
			}
			
			List<Book> bookList = bookDao.getBookList(u.getUserID());
			
			if(bookList.size() > 0) {
				mv.addObject("bookList", bookList);
			}
			
			session.setAttribute("user", u);
			mv.setViewName("user-home");
			return mv;

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			ModelAndView mv = new ModelAndView();
			mv.setViewName("error");
			return mv;
		}

	}
	
	
	
	
	
	
	@RequestMapping(value = "/user/goHome", method = RequestMethod.GET)
	protected ModelAndView gohome(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		ModelAndView mv = new ModelAndView();
		System.out.print("go Home");

		User u = (User) request.getSession().getAttribute("user");
		
		
		
		List<Book> bookList = bookDao.getBookList(u.getUserID());
		
		if(bookList.size() > 0) {
			mv.addObject("bookList", bookList);
		}
		
		
		mv.setViewName("user-home");
		return mv;

	}
	
	
	
	
	
}
