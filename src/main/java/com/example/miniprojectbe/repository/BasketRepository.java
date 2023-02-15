package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.dto.BasketId;
import com.example.miniprojectbe.entity.Basket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, BasketId> {

    @EntityGraph(attributePaths = {"member", "item"})
    List<Basket> findByMember_MemberId(String memberId);
}
