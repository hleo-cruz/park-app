package com.br.park.mapper;

import com.br.park.api.dto.response.SpotStatusResponseDTO;
import com.br.park.infrastructure.repository.spotStatus.SpotStatus;

import java.math.BigDecimal;

public class SpotStatusMapper {

    public static SpotStatusResponseDTO toResponse(SpotStatus spotStatus, BigDecimal price) {
        return SpotStatusResponseDTO.builder()
                .licensePlate(spotStatus.getLicensePlate())
                .entryTime(spotStatus.getEntryTime())
                .ocupied(spotStatus.getOccupied())
                .priceUntilNow(price)
                .timeParked(spotStatus.getTimeParked())
                .build();
    }
}