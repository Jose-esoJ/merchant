package com.example.merchant.repository;

import com.example.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    List<Merchant> findByNameContainingAndCityContainingAndStatusContaining(String name, String city, String status, Pageable pageable);
}
