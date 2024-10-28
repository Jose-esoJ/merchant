package com.example.merchant.repository;

import com.example.merchant.entity.AuthenticationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthenticationRequestRepository extends JpaRepository<AuthenticationRequest, Long> {
    Optional<AuthenticationRequest> findByEmail(String email);
}