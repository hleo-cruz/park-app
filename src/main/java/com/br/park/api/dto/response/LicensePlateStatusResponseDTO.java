package com.br.park.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LicensePlateStatusResponseDTO {

    @JsonProperty(value = "license_plate")
    private String licensePlate;
    @JsonProperty(value = "price_until_now")
    private BigDecimal priceUntilNow;
    @JsonProperty(value = "entry_time")
    private LocalDateTime entryTime;
    @JsonProperty(value = "time_parked")
    private LocalDateTime timeParked;
    @JsonProperty(value = "lat")
    private String lat;
    @JsonProperty(value = "lng")
    private String lng;

}
