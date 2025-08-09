package com.repositories;

import com.entities.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	boolean existsByBookIdAndReturnedFalse(Integer bookId);
	List<Loan> findByUserIdAndReturnedFalse(Integer userId);
	
}

