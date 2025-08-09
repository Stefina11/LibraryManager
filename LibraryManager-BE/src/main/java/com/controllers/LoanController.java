package com.controllers;


import com.dtos.LoanDto;
import com.entities.Book;
import com.entities.Loan;
import com.entities.User;
import com.services.BookService;
import com.services.LoanService;
import com.services.UserService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addLoan(@RequestBody LoanDto loanDto) {
      
        Book book = bookService.getBookById(loanDto.getBookId());       
        User user =  userService.getUserById(loanDto.getUserId());
        
        if (loanService.isBookOnLoan(book.getId())) {
            return "Il libro non è disponibile per il prestito.";
        }
                
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);   
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(2));  
        loan.setReturned(false);   

        loanService.addLoan(loan);
        return "Prestito aggiunto con successo!";
    }

    @PostMapping("/return/{loanId}")
    public String returnBook(@PathVariable Integer loanId) {
        return loanService.returnBook(loanId);
    }

    // Recuperare un prestito per ID
    @GetMapping("/{loanId}")
    public Loan getLoan(@PathVariable Integer loanId) {
        return loanService.getLoanById(loanId)
                .orElseThrow(() -> new RuntimeException("Prestito non trovato!"));
    }
    
 // Verifica se un libro è in prestito
    @GetMapping("/check/{bookId}")
    public boolean isBookOnLoan(@PathVariable Integer bookId) {
        return loanService.isBookOnLoan(bookId);
    }

 // 6) Prestiti attivi di un utente
    @GetMapping("/user")
    public List<LoanDto> getUserActiveLoans() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));


        return loanService.getActiveLoansByUserId(user.getId())
            .stream()
            .map(loan -> {
                LoanDto dto = new LoanDto();
                dto.setLoanId(loan.getId());
                dto.setBookId(loan.getBook().getId());
                dto.setUserId(loan.getUser().getId());
                dto.setLoanDate(loan.getLoanDate());
                dto.setDueDate(loan.getDueDate());
                dto.setReturned(loan.isReturned());

                Book book = loan.getBook();
                dto.setBookTitle(book.getTitle());
                dto.setBookAuthor(book.getAuthor());
                dto.setBookIsbn(book.getIsbn());

                return dto;
            })
            .toList();
    }

}

