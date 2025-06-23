package com.br.park.mapper;

import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.infrastructure.repository.parklot.ParkLot;

public class ParkLotMapper {

    public static ParkLot toEntity(ParkLotRequestDTO dto) {
        return ParkLot.builder()
                .licensePlate(dto.getLicensePlate())
                .parkedTime(dto.getEntryTime())
                .status(dto.getEventType().getStatus())
                .build();
    }
}