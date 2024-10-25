package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MerchantDTO> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantService.save(merchantDTO));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        merchantService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
