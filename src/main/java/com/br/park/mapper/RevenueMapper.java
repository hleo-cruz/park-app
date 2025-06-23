package com.br.park.mapper;

import com.br.park.api.dto.response.RevenueResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RevenueMapper {

    public static RevenueResponseDTO toResponse(BigDecimal totalOfDay, LocalDate currentDate) {
        return RevenueResponseDTO.builder()
                .currency("BRL")
                .amount(totalOfDay)
                .timestamp(currentDate)
                .build();
    }
}