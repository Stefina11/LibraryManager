package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtos.BookDto;
import com.dtos.BookResponseDto;
import com.entities.Book;
import com.services.BookService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	@PostMapping ("/add") 
	 @PreAuthorize("hasRole('ADMIN')")
	public String addBook(@RequestBody @Valid BookDto bookDto) {
		Book book = new Book();
		book.setTitle(bookDto.getTitle());
		book.setAuthor(bookDto.getAuthor());
		book.setCategory(bookDto.getCategory());
		book.setIsbn(bookDto.getIsbn());
		bookService.addBook(book);
		return "libro aggiunto con successo!";
		
	}
	
	@GetMapping("/search")
	public List<BookResponseDto> searchBooks(@RequestParam String query) {
		return bookService.searchByQuery(query);
	}
	
    @GetMapping
    public List<BookResponseDto> getAllBooks() {  
        return bookService.getAllBooks();
    }
    
    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        return new BookResponseDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getCategory(),
            true,  
            book.getIsbn()
        );
    }

}
