package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entities.LoanHistory;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Integer> {
}
