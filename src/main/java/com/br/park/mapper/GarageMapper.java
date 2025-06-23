package com.br.park.mapper;

import com.br.park.infrastructure.client.SectorDto;
import com.br.park.infrastructure.client.SpotDto;

import com.br.park.infrastructure.repository.sector.Sector;
import com.br.park.infrastructure.repository.spot.Spot;

import java.time.LocalTime;

public class GarageMapper {

    public static Sector toSector(SectorDto dto) {
        return Sector.builder()
                .sector(dto.getSector())
                .basePrice(dto.getBase_price())
                .maxCapacity(dto.getMax_capacity())
                .openHour(LocalTime.parse(dto.getOpen_hour()))
                .closeHour(LocalTime.parse(dto.getClose_hour()))
                .durationLimitMinutes(dto.getDuration_limit_minutes())
                .build();
    }

    public static Spot toSpot(SpotDto dto) {
        return Spot.builder()
                .id(dto.getId())
                .sector(dto.getSector())
                .lat(dto.getLat())
                .lng(dto.getLng())
                .occupied(dto.isOccupied())
                .build();
    }
}
