package com.neu.onlineBMP.CONTROLLER;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.neu.onlineBMP.DAO.BookDAO;
import com.neu.onlineBMP.DAO.UserDAO;
import com.neu.onlineBMP.POJO.Book;
import com.neu.onlineBMP.POJO.Message;
import com.neu.onlineBMP.POJO.User;
import com.neu.onlineBMP.VALIDATOR.UserValidator;

@Controller
public class BookController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("bookDao")
	BookDAO bookDao;
	
	
	@Autowired
    ServletContext servletContext;
	
	

	
	private static String uploadPath = "/Users/vivekshetye/SpringToolSuite/OBMPimages";
	
	@RequestMapping(value = "user/addBook", method = RequestMethod.GET)
	protected ModelAndView addBook() throws Exception {
		System.out.print("Add Book");
		
		
		return new ModelAndView("add-book","book",new Book());

	}
	
	@RequestMapping(value = "user/search", method = RequestMethod.POST)
	@ResponseBody
	protected List<Book> searchBook(HttpServletRequest request,@RequestParam(value="searchData") String searchData) throws Exception {
		System.out.print("********************Add Book******************");
		
		
		List<Book> bookList = new ArrayList<Book>();
		
		try{
		
		String bookName = searchData;

		
		System.out.println("\n\n\n\n\n\n*****************Search Book name :"+bookName+"******************\n\n\n\n\n");
		User user = (User) request.getSession().getAttribute("user");
		bookList = bookDao.getBookSearchList(bookName, user);
		ModelAndView mv = new ModelAndView();
		
		System.out.println("\n\n\n\n\n\n*****************Book list size :"+bookList.size()+"******************\n\n\n\n\n");
		for(Book b : bookList) {
			
			System.out.println("\n\n\n\n********************"+b.getBookName()+"*****************\n\n\n\n\n");
		}
		
		
		
//		mv.addObject(book);
		mv.addObject(bookList);
		mv.setViewName("home");
		
		System.out.println("************GOT BOOK************");
		}
		catch(Exception e) {
			System.out.println("\n\n\n\n\n****************Exception caused is:"+e+"***************\n\n\n\n\n\n");
		}
		return bookList;

	}
	
	
	
	
	
	
	
	@RequestMapping(value = "user/searchAll", method = RequestMethod.POST)
	@ResponseBody
	protected List<Book> searchAllBook(HttpServletRequest request,@RequestParam(value="searchData") String searchData) throws Exception {
		System.out.print("********************Add Book******************");
		//Book book = null;
		
		List<Book> bookList = new ArrayList<Book>();
		
		try{
		
		String bookName = searchData;

		
		System.out.println("\n\n\n\n\n\n*****************Search Book name :"+bookName+"******************\n\n\n\n\n");
		User user = (User) request.getSession().getAttribute("user");
		bookList = bookDao.getAllBookSearchList( user);
		ModelAndView mv = new ModelAndView();
		
		System.out.println("\n\n\n\n\n\n*****************Book list size :"+bookList.size()+"******************\n\n\n\n\n");
		for(Book b : bookList) {
			
			System.out.println("\n\n\n\n********************"+b.getBookName()+"*****************\n\n\n\n\n");
		}
		
		
		

		mv.addObject(bookList);
		mv.setViewName("home");
		
		System.out.println("************GOT BOOK************");
		}
		catch(Exception e) {
			System.out.println("\n\n\n\n\n****************Exception caused is:"+e+"***************\n\n\n\n\n\n");
		}
		return bookList;

	}
	
	
	
	
	
	
	
	@RequestMapping(value = "user/createBook", method = RequestMethod.POST)
	protected ModelAndView addBook(HttpServletRequest request,  @ModelAttribute("book") Book book, BindingResult result) throws Exception {
		System.out.print("creating  Book");

		Boolean flag = true;
		request.setAttribute("bookStatus" ,flag );
		HttpSession session = request.getSession();
		
		
		
		
		
            File directory;
            String check = File.separator; // Checking if system is linux
                                            // based or windows based by
                                            // checking seprator used.
            
            CommonsMultipartFile photoInMemory = book.getPhoto();

            String fileName = photoInMemory.getOriginalFilename();
            

            directory = new File(uploadPath + check + fileName);
            boolean temp = directory.exists();
            if (!temp) {
                temp = directory.mkdir();
            }
            if (temp) {
                
                photoInMemory.transferTo(directory);
                book.setFilename(check+"bookImages"+check+fileName);
               
                

            } else {
                System.out.println("Failed to create directory!");
            }
        
		
		
		bookDao.addBook(book, (User) session.getAttribute("user"));
		return new ModelAndView("add-book", "book", new Book());

	}
	

	
	@RequestMapping(value = "user/deleteBook", method = RequestMethod.GET)
	protected ModelAndView deleteBook(HttpServletRequest request, @RequestParam(value="id") long bookId) throws Exception {
		System.out.print("deleting messges");
		
		long bId = bookId;

		
		
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		bookDao.deleteBook(bId,user);
		
		List<Book> bookList = bookDao.getMyShelfList(user);
		

		
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myshelf");
		
		mv.addObject("bookList", bookList);

		return mv;

	}

	
	@RequestMapping(value = "user/myShelf", method = RequestMethod.GET)
	protected ModelAndView myShelf(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		User user = (User) request.getSession().getAttribute("user");
		
		List<Book> bookList = bookDao.getMyShelfList(user);	
		mv.addObject(bookList);
		mv.setViewName("myshelf");
		return mv;
	}
	
	
}
