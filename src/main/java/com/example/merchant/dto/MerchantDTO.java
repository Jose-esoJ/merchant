package com.example.merchant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantDTO {
    private Long id;
    private String name;
    private String department;
    private String city;
    private String phone;
    private String email;
    private Date registrationDate;
    private String status;
    private Double totalIncome;
    private Integer employeeCount;
}
