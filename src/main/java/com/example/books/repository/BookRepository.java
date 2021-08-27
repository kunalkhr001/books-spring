package com.example.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.books.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleIsContaining(String title);
	
	@Query(value = "SELECT * FROM books where bookid=?1 limit 1", nativeQuery = true)
	Book findAllBookID(Long bookID);
}
