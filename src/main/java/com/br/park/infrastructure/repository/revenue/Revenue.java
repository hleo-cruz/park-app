package com.br.park.infrastructure.repository.revenue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {

    private Integer id;
    private String sector;
    private String lat;
    private String lng;
    private Boolean occupied;
    private BigDecimal finalPrice;
}
