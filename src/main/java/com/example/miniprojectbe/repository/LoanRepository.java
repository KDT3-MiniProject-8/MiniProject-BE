package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByCategory(String category);
    List<Loan> findByBankAndCategory(String bank, String category);
}
