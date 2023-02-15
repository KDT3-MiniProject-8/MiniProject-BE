package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Deposit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Slice<Deposit> findAllByCategory(String category, PageRequest pageRequest);

    List<Deposit> findAllByBankContainingOrItemNameContaining(String content1, String content2);

}
