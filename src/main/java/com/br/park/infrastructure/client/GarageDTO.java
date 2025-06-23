package com.br.park.infrastructure.client;

import lombok.Data;

import java.util.List;

@Data
public class GarageDTO {
    private List<SectorDto> garage;
    private List<SpotDto> spots;
}
