package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    @Operation(summary = "Get all merchants with pagination and filters")
    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String status) {
        List<MerchantDTO> merchants = merchantService.getAllMerchants(name, city, status);
        return ResponseEntity.ok(merchants);
    }

    @Operation(summary = "Get merchant by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @Operation(summary = "Create new merchant")
    @PostMapping
    public ResponseEntity<MerchantDTO> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantService.createMerchant(merchantDTO));
    }

    @Operation(summary = "Update merchant details")
    @PutMapping("/{id}")
    public ResponseEntity<MerchantDTO> updateMerchant(@PathVariable Long id, @RequestBody MerchantDTO merchantDTO) {
        return ResponseEntity.ok(merchantService.updateMerchant(id, merchantDTO));
    }

    @Operation(summary = "Delete a merchant by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update state by ID")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        merchantService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
