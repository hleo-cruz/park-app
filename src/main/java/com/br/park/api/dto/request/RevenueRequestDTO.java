package com.br.park.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueRequestDTO {

    @JsonProperty(value = "sector")
    private String sector;
    @JsonProperty(value = "date")
    private LocalDate date;
}
