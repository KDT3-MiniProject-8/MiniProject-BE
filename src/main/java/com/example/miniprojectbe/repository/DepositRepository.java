package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Deposit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {


    Slice<Deposit> findAllByBankContainingOrItemNameContaining(String content1, String content2, PageRequest pageRequest);
    Slice<Deposit> findAllByCategory(String category, PageRequest pageRequest);
    List<Deposit> findTop3ByBankAndCategoryOrderByPrefRateDesc(String bank, String category);
    Slice<Deposit> findByBankOrCategoryOrPreferenceOrTargetOrderByPrefRateDesc(String bank, String category, String preference, String target, PageRequest pageRequest);

    Optional<Deposit> findByItemId(Long itemId);

}
