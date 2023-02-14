package com.example.miniprojectbe.repository;

import com.example.miniprojectbe.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BlacklistRepository extends JpaRepository<Blacklist, String> {

    boolean existsByToken(String token);
}
