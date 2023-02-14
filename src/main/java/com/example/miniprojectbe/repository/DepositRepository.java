package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findAllByCategory(String category);
}
