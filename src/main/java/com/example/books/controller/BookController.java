package com.example.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.model.Book;
import com.example.books.repository.BookRepository;

@RestController
@RequestMapping("/")
public class BookController {
	@Autowired
    BookRepository bookRepository;
	
	@GetMapping("/")
	public List<Book> getAllBooks() {
	    return bookRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable(value = "id") Long bookId) {
	    return bookRepository.findAllBookID(bookId);
	}
	
	@GetMapping("/search/{term}")
	public List<Book> searchByTitle(@PathVariable(value = "term") String term) {
		return bookRepository.findByTitleIsContaining(term);
	}
}
