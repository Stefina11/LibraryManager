package com.services;

import com.dtos.BookResponseDto;
import com.entities.Book;
import com.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional 
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LoanService loanService;

    @Autowired
    public BookService(BookRepository bookRepository, LoanService loanService) {
        this.bookRepository = bookRepository;
        this.loanService = loanService;
    }

    public Integer addBook(Book book) {
        return bookRepository.save(book).getId();
    }
    
    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con ID: " + bookId));
    }
    
    public List<BookResponseDto> searchByQuery(String query) {
        return bookRepository.searchByQuery(query)
            .stream()
            .map(b -> new BookResponseDto(
                b.getId(), 
                b.getTitle(),
                b.getAuthor(), 
                b.getCategory(),
                !loanService.isBookOnLoan(b.getId()),
                b.getIsbn()
            ))
            .toList();
    }
    
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
            .map(b -> new BookResponseDto(
                b.getId(),
                b.getTitle(),
                b.getAuthor(),
                b.getCategory(),
                !loanService.isBookOnLoan(b.getId()),
                b.getIsbn()
            ))
            .toList();
    }
}

