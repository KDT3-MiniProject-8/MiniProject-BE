package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByMemberId(String memberId);
    boolean existsByMemberId(String email);
}
