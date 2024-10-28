package com.example.merchant.service;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.entity.Merchant;
import com.example.merchant.entity.Establishment;
import com.example.merchant.repository.EstablishmentRepository;
import com.example.merchant.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    private final EstablishmentRepository establishmentRepository;

    public List<MerchantDTO> getAllMerchants( String name, String city, String status) {
       List<Merchant> merchants = merchantRepository.findByNameContainingAndCityContainingAndStatusContaining(name, city, status);
        return merchants.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public MerchantDTO getMerchantById(Long id) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Merchant not found"));
        return convertToDto(merchant);
    }

    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = convertToEntity(merchantDTO);
        merchantRepository.save(merchant);
        return convertToDto(merchant);
    }

    public MerchantDTO updateMerchant(Long id, MerchantDTO merchantDTO) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Merchant not found"));
        merchant.setName(merchantDTO.getName());
        merchant.setDepartment(merchantDTO.getDepartment());
        merchant.setCity(merchantDTO.getCity());
        merchant.setPhone(merchantDTO.getPhone());
        merchant.setEmail(merchantDTO.getEmail());
        merchant.setStatus(merchantDTO.getStatus());
        merchantRepository.save(merchant);
        return convertToDto(merchant);
    }

    public void deleteMerchant(Long id) {
        merchantRepository.deleteById(id);
    }

    public void updateStatus(Long id, String status) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Merchant not found"));
        merchant.setStatus(status);
        merchantRepository.save(merchant);
    }

    private MerchantDTO convertToDto(Merchant merchant) {
        MerchantDTO dto = new MerchantDTO();
        dto.setId(merchant.getId());
        dto.setName(merchant.getName());
        dto.setDepartment(merchant.getDepartment());
        dto.setCity(merchant.getCity());
        dto.setPhone(merchant.getPhone());
        dto.setEmail(merchant.getEmail());
        dto.setRegistrationDate(merchant.getRegistrationDate());
        dto.setStatus(merchant.getStatus());
        dto.setTotalIncome(calculateTotalIncome(merchant));
        dto.setEmployeeCount(calculateEmployeeCount(merchant));
        return dto;
    }

    private Merchant convertToEntity(MerchantDTO dto) {
        Merchant merchant = new Merchant();
        merchant.setDepartment(dto.getDepartment());
        merchant.setCity(dto.getCity());
        merchant.setPhone(dto.getPhone());
        merchant.setEmail(dto.getEmail());
        merchant.setRegistrationDate(dto.getRegistrationDate());
        merchant.setStatus(dto.getStatus());
        return merchant;
    }

    private Double calculateTotalIncome(Merchant merchant) {
        return establishmentRepository.findByMerchantId(merchant.getId())
                .stream()
                .mapToDouble(Establishment::getIncome)
                .sum();
    }

    private Integer calculateEmployeeCount(Merchant merchant) {
        return establishmentRepository.findByMerchantId(merchant.getId())
                .stream()
                .mapToInt(Establishment::getEmployeeCount)
                .sum();
    }
}