package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByMemberId(String memberId);
    boolean existsByMemberId(String email);
    Optional<Member> findByMemberIdAndName(String memberId, String name);

    @Modifying
    @Query("update Member m set m.password = :password where m.memberId = :memberId")
    void updateMemberPassword(@Param("memberId") String memberId, @Param("password") String password);
}
