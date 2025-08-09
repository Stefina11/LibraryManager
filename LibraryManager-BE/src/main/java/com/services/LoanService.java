package com.services;


import com.entities.Loan;
import com.entities.LoanHistory;
import com.repositories.LoanHistoryRepository;
import com.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanHistoryRepository loanHistoryRepository;

    
    
    
    
    public void addLoan(Loan loan) {
        loanRepository.save(loan);
    }

    
    public Optional<Loan> getLoanById(Integer id) {
        return loanRepository.findById(id);  
    }
   
    
    
    public String returnBook(Integer loanId) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();

            // new record wiyìth loan history
            LoanHistory loanHistory = new LoanHistory();
            loanHistory.setBook(loan.getBook());
            loanHistory.setUser(loan.getUser());
            loanHistory.setLoanDate(loan.getLoanDate());
            loanHistory.setDueDate(loan.getDueDate());
            loanHistory.setReturnDate(LocalDate.now()); //  return date
            loanHistoryRepository.save(loanHistory);

            // delete loan from loans
            loanRepository.delete(loan);

            return "Libro restituito con successo!";
        } else {
            return "Prestito non trovato!";
        }
    }
    
    public boolean isBookOnLoan(Integer bookId) {
        return loanRepository.existsByBookIdAndReturnedFalse(bookId);
    }
    

    public List<Loan> getActiveLoansByUserId(Integer userId) {
        return loanRepository.findByUserIdAndReturnedFalse(userId);
    }


    
}

