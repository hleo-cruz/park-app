package com.br.park.services.chain;

import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCapacityResultDTO {

    private BigDecimal charge;
    private TypeChargeApplyEnum typeChargeApplyEnum;
}
