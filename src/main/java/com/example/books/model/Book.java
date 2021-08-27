package com.example.books.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "books",
indexes = {@Index(name = "index_on_bookid", columnList="bookid", unique = false)})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id", "createdAt", "updatedAt"}, 
allowGetters = true)

public class Book implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long bookID;
	
	@Lob
    private String title;

    @Column(columnDefinition = "TEXT")
    private String authors;
    
    private Double average_rating;
    
    private String language_code; 
    
    private String isbn;

    private Long ratings_count;

    private Long price;

    public Long getBookID() {
		return bookID;
	}

	public void setBookID(Long bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Double getAverage_rating() {
		return average_rating;
	}
	
	public void setAverage_rating(String average_rating) {
		try {
			this.average_rating = Double.parseDouble(average_rating);
		}
		catch (Exception e) {
			// TODO: handle exception
			this.average_rating = (double) 0;
		}
	}

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = String.valueOf(isbn);
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getRatings_count() {
		return ratings_count;
	}

	public void setRatings_count(String ratings_count) {
		try {
			this.ratings_count = Long.parseLong(ratings_count);
		}
		catch (Exception e) {
			// TODO: handle exception
			this.ratings_count = (long) 0;
		}
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
