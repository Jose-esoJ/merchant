package com.example.merchant.controller;

import com.example.merchant.dto.EstablishmentDTO;
import com.example.merchant.service.EstablishmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/establishments")
public class EstablishmentController {

    private final  EstablishmentService establishmentService;

    @Operation(summary = "Get establishment by ID")
    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<List<EstablishmentDTO>> getEstablishmentsByMerchantId(@PathVariable Long merchantId) {
        return ResponseEntity.ok(establishmentService.getEstablishmentsByMerchantId(merchantId));
    }

    @Operation(summary = "Create new establishment")
    @PostMapping
    public ResponseEntity<EstablishmentDTO> createEstablishment(@RequestBody EstablishmentDTO establishmentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(establishmentService.createEstablishment(establishmentDTO));
    }

    @Operation(summary = "Update establishment details")
    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentDTO> updateEstablishment(@PathVariable Long id, @RequestBody EstablishmentDTO establishmentDTO) {
        return ResponseEntity.ok(establishmentService.updateEstablishment(id, establishmentDTO));
    }

    @Operation(summary = "Delete an establishment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Long id) {
        establishmentService.deleteEstablishment(id);
        return ResponseEntity.noContent().build();
    }
}