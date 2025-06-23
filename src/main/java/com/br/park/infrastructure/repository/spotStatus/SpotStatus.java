package com.br.park.infrastructure.repository.spotStatus;

import com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum;
import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotStatus {

    private String licensePlate;
    private BigDecimal priceUntilNow;
    private BigDecimal charge;
    private LocalDateTime entryTime;
    private LocalDateTime timeParked;
    private Boolean occupied;
    private ParkLotStatusEnum parklotStatus;
    private String lat;
    private String lng;
    private BigDecimal basePrice;
    private Integer duration;
    private TypeChargeApplyEnum typeChargeApply;
}
