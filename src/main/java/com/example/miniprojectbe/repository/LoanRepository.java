package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Loan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Slice<Loan> findAllByCategory(String category, PageRequest pageRequest);
    List<Loan> findTop3ByBankAndCategoryOrderByMinRateAsc(String bank, String category);
    List<Loan> findAllByBankContainingOrItemNameContaining(String content1, String content2);
}
