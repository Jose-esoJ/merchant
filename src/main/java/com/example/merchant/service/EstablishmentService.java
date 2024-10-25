package com.example.merchant.service;

import com.example.merchant.dto.EstablishmentDTO;
import com.example.merchant.entity.Establishment;
import com.example.merchant.repository.EstablishmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    public List<EstablishmentDTO> getEstablishmentsByMerchantId(Long merchantId) {
        List<Establishment> establishments = establishmentRepository.findByMerchantId(merchantId);
        return establishments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public EstablishmentDTO createEstablishment(EstablishmentDTO establishmentDTO) {
        Establishment establishment = convertToEntity(establishmentDTO);
        establishmentRepository.save(establishment);
        return convertToDto(establishment);
    }

    public EstablishmentDTO updateEstablishment(Long id, EstablishmentDTO establishmentDTO) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Establishment not found"));
        establishment.setName(establishmentDTO.getName());
        establishment.setIncome(establishmentDTO.getIncome());
        establishment.setEmployeeCount(establishmentDTO.getEmployeeCount());
        establishmentRepository.save(establishment);
        return convertToDto(establishment);
    }

    public void deleteEstablishment(Long id) {
        establishmentRepository.deleteById(id);
    }

    private EstablishmentDTO convertToDto(Establishment establishment) {
        EstablishmentDTO dto = new EstablishmentDTO();
        dto.setId(establishment.getId());
        dto.setName(establishment.getName());
        dto.setIncome(establishment.getIncome());
        dto.setEmployeeCount(establishment.getEmployeeCount());
        dto.setMerchantId(establishment.getMerchant().getId());
        return dto;
    }

    private Establishment convertToEntity(EstablishmentDTO dto) {
        Establishment establishment = new Establishment();
        establishment.setName(dto.getName());
        establishment.setIncome(dto.getIncome());
        establishment.setEmployeeCount(dto.getEmployeeCount());
        return establishment;
    }
}