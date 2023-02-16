package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Deposit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {


    List<Deposit> findAllByBankContainingOrItemNameContaining(String content1, String content2);
    Slice<Deposit> findAllByCategory(String category, PageRequest pageRequest);
    List<Deposit> findTop3ByBankAndCategoryOrderByRateDesc(String bank, String category);
    Slice<Deposit> findByBankOrCategoryOrPreferenceOrTargetOrderByRateDesc(String bank, String category, String preference, String target, PageRequest pageRequest);

}
