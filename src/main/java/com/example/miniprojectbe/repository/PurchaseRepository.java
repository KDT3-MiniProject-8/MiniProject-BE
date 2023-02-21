package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.PurchaseId;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.entity.Purchase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseId> {

    Optional<Purchase> findByPurchaseId(Long purchaseId);

    boolean existsByMemberAndItem(Member member, Item item);

    @EntityGraph(attributePaths = {"member", "item"})
    @Query("SELECT b FROM Purchase b WHERE b.member.memberId = :memberId AND b.item.category IN (:category)")
    Slice<Purchase> findByMemberIdAndCategory(String memberId, List<String> category, PageRequest pageRequest);

}
