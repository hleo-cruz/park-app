package com.br.park.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueResponseDTO {

    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "amount")
    private BigDecimal amount;
    @JsonProperty(value = "timestamp")
    private LocalDate timestamp;

}
