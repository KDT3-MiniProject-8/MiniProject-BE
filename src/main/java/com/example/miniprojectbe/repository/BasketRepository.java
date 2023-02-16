package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.dto.BasketId;
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
    List<Basket> findByMember_MemberId(String memberId);

    Optional<Basket> findByBasket(Long basketId);

    @Modifying
    @Query("delete from Basket basket where basket.member.memberId = :memberId")
    void deleteByMemberId(@Param("memberId") String memberId);

    boolean existsByMemberAndItem(Member member, Item item);

    @EntityGraph(attributePaths = {"member", "item"})
    Slice<Basket> findByMember_MemberIdAndItem_CategoryOrItem_Category(String memberId, String category1, String category2, PageRequest pageRequest);
}
