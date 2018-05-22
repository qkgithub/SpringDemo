package com.java1234.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.dao.BookDao;
import com.java1234.entity.Book;


@Controller
@RequestMapping("/book")
public class BookController {
	@Resource
	private BookDao bookDao;
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("bookList", bookDao.findAll());
		modelAndView.setViewName("bookList");
		return modelAndView;
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Book book) {
		bookDao.save(book);
		return "/book/list";
	}
	@GetMapping("/preUpdate/{id}")
	public ModelAndView preUpdate(@PathVariable("id") Integer id) {
		ModelAndView modelAndView=new ModelAndView();
		System.out.println(bookDao.getOne(id));
		modelAndView.addObject("book", bookDao.getOne(id));
		modelAndView.setViewName("bookUpdate");
		return modelAndView;
	}
	@PostMapping("/update")
	public String update(Book book) {
		System.out.println(book);
		bookDao.save(book);
		return "/book/list";
	}
	@GetMapping("/delete")
	public String delete(Integer id) {
		bookDao.delete(id);
		return "/book/list";
	}
	@RequestMapping("/bookAdd")
	public String preAdd() {
		return "/bookAdd.html";
	}
	@ResponseBody
	@RequestMapping("/queryByName")
	public List<Book> findByName(String name) {
		List<Book> list=bookDao.findByName(name);
		return list;
	}
	@ResponseBody
	@GetMapping("/random")
	public List<Book> randomList(Integer n) {
		
		List<Book> randomList=bookDao.randomList(n);
		return randomList;
	}
//	@RequestMapping("/list2")
//	public ModelAndView list2(Book book) {
//		
//		ModelAndView modelAndView=new ModelAndView();
//		List<Book> bookList=bookDao.findAll(new Specification<Book>() {
//			@Override
//			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				Predicate predicate=cb.conjunction();
//				if (book!=null) {
//					if (book.getName()!=null&&!"".equals(book.getName())) {
//						predicate.getExpressions().add(cb.like(root.get("name"),"%"+book.getName()+"%"));
//					}
//					if (book.getAuthor()!=null&&!"".equals(book.getAuthor())) {
//						predicate.getExpressions().add(cb.like(root.get("author"),"%"+book.getAuthor()+"%"));
//					}
//				}
//				System.out.println(predicate);
//				return predicate;
//			}
//		});
//		modelAndView.addObject("moduleBook", book);
//		modelAndView.addObject("bookList", bookList);
//		modelAndView.setViewName("bookList");
//		
//		return modelAndView;
//	}
	@RequestMapping("/orderById")
	public ModelAndView orderById() {
		ModelAndView modelAndView=new ModelAndView();
		List<Book> bookList=bookDao.orderById();
		modelAndView.addObject("bookList", bookList);
		modelAndView.setViewName("bookList");
		return modelAndView;
	}
	
	@RequestMapping("/list2")
	public ModelAndView list2(Book book) {
		System.out.println(11);
		ModelAndView modelAndView=new ModelAndView();
		List<Book> bookList=bookDao.findAll(new Specification<Book>() {
			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if (book!=null) {
					if (book.getName()!=null&&!"".equals(book.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"),"%"+book.getName()+"%"));
					}
					if (book.getAuthor()!=null&&!"".equals(book.getAuthor())) {
						predicate.getExpressions().add(cb.like(root.get("author"),"%"+book.getAuthor()+"%"));
					}
				}
				return predicate;
			}
		});
		modelAndView.addObject("bookList",bookList);
		modelAndView.setViewName("bookList");
		return modelAndView;
	}
	
	
	
}
