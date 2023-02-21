package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.BasketId;
import com.example.miniprojectbe.entity.Basket;
import com.example.miniprojectbe.entity.Item;
import com.example.miniprojectbe.entity.Member;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, BasketId> {

    @EntityGraph(attributePaths = {"member", "item"})
    Optional<Basket> findByMember_MemberIdAndItem_ItemId(String memberId, Long itemId);

    Optional<Basket> findByBasket(Long basketId);

    @Modifying
    @Query("delete from Basket basket where basket.member.memberId = :memberId")
    void deleteByMemberId(@Param("memberId") String memberId);

    boolean existsByMemberAndItem(Member member, Item item);

    @EntityGraph(attributePaths = {"member", "item"})
    @Query("SELECT b FROM Basket b WHERE b.member.memberId = :memberId AND b.item.category IN (:category)")
    Slice<Basket> findByMemberIdAndCategory(String memberId, List<String> category, PageRequest pageRequest);
}
