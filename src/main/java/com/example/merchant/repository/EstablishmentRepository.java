package com.example.merchant.repository;

import com.example.merchant.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
    List<Establishment> findByMerchantId(Long merchantId);
}