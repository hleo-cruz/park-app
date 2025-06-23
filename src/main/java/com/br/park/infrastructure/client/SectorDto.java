package com.br.park.infrastructure.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SectorDto {
    private String sector;
    private BigDecimal base_price;
    private int max_capacity;
    private String open_hour;
    private String close_hour;
    private int duration_limit_minutes;
}

