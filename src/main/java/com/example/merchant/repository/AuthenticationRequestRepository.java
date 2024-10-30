package com.example.merchant.repository;

import com.example.merchant.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthenticationRequestRepository extends JpaRepository<Authentication, Long> {
    Optional<Authentication> findByEmail(String email);
}