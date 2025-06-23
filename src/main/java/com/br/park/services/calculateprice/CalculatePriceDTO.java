package com.br.park.services.calculateprice;

import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CalculatePriceDTO {

    LocalDateTime entryTime;
    LocalDateTime exitTime;
    BigDecimal basePrice;
    BigDecimal charge;
    Integer durationLimitMinutes;
    TypeChargeApplyEnum typeChargeApply;
}
