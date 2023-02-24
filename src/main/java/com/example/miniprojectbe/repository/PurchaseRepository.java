package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.PurchaseId;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import com.example.miniprojectbe.entity.Purchase;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseId> {

    Optional<Purchase> findByPurchaseId(Long purchaseId);

    @EntityGraph(attributePaths = {"member", "item"})
    Optional<Purchase> findByMember_MemberIdAndItem_ItemId(String memberId, Long itemId);

    boolean existsByMemberAndItemAndStatus(Member member, Item item, String status);

    boolean existsByMember_MemberIdAndItem_ItemIdAndStatus(String memberId, Long itemId, String status);

    @EntityGraph(attributePaths = {"member", "item"})
    @Query("SELECT b FROM Purchase b WHERE b.member.memberId = :memberId AND b.item.category IN (:category)")
    List<Purchase> findByMemberIdAndCategory(String memberId, List<String> category);

    Integer countByMember_MemberIdAndStatus(String memberId, String status);

}
