package com.br.park.mapper;

import com.br.park.api.dto.response.LicensePlateStatusResponseDTO;
import com.br.park.infrastructure.repository.licensePlateStatus.LicensePlateStatus;

import java.math.BigDecimal;

public class LicensePlateStatusMapper {

    public static LicensePlateStatusResponseDTO toResponse(LicensePlateStatus licensePlateStatus, BigDecimal price) {
        return LicensePlateStatusResponseDTO.builder()
                .licensePlate(licensePlateStatus.getLicensePlate())
                .lat(licensePlateStatus.getLat())
                .lng(licensePlateStatus.getLng())
                .timeParked(licensePlateStatus.getTimeParked())
                .entryTime(licensePlateStatus.getEntryTime())
                .priceUntilNow(price)
                .build();
    }
}