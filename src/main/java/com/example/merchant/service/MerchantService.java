package com.example.merchant.service;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public List<MerchantDTO> findAll() {
        // Convertir a DTO
        return merchantRepository.findAll();
    }

    public MerchantDTO findById(Long id) {
        // Convertir a DTO
        return merchantRepository.findById(id).orElseThrow();
    }

    public MerchantDTO save(MerchantDTO merchantDTO) {
        // Convertir a Entidad, Guardar, y regresar DTO
        return null;
    }

    public void updateStatus(Long id, String status) {
        // Lógica de actualización
    }
}
