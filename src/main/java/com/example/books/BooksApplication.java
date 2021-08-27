package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class BooksApplication {
	@Autowired
	BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	
	@Bean
	CommandLineRunner seedBooksData() {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			InputStream inputStream = TypeReference.class.getResourceAsStream("https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json");
			InputStream inputStream = new URL("https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json").openStream();
			String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//			System.out.println(text);
			try {
//				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//				while(reader.ready()) {
//				     String line = reader.readLine();
//				     Book book = mapper.readValue(line, Book.class);
//				}
				List<Book> books = mapper.readValue(text,new TypeReference<List<Book>>(){});
				for(Book b:books)
				{	
					boolean exists = bookRepository.findAllBookID(b.getBookID())!=null;
					if(!exists)
						bookRepository.save(b);
				}
				System.out.println("Books Saved!");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Unable to save books: " + e.getMessage());
			}
		};
	}
}
