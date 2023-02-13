package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
