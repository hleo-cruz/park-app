package com.br.park.infrastructure.client;

import lombok.Data;

import lombok.Data;

@Data
public class SpotDto {
    private int id;
    private String sector;
    private String lat;
    private String lng;
    private boolean occupied;
}

