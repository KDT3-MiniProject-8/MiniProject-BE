package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Loan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Slice<Loan> findAllByCategory(String category, PageRequest pageRequest);

    Slice<Loan> findAllByBankContainingOrItemNameContaining(String content1, String content2, PageRequest pageRequest);

    List<Loan> findTop3ByBankAndCategoryOrderByMinRateAsc(String bank, String category);

    Slice<Loan> findByBankOrCategoryOrPreferenceOrTargetOrderByMinRateAsc(String bank, String category, String preference, String target, PageRequest pageRequest);

    Optional<Loan> findByItemId(Long itemId);

}
