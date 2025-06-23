package com.br.park.api.dto.request;

import com.br.park.api.EventTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkLotRequestDTO {
    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonProperty("entry_time")
    private LocalDateTime entryTime;

    @JsonProperty("exit_time")
    private LocalDateTime exitTime;

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lng")
    private String longitude;

    @JsonProperty("event_type")
    private EventTypeEnum eventType;
}
