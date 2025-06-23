package com.br.park.services.chain;

import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;

import java.math.BigDecimal;

public class TestCapacityResultFactory {

    public static TestCapacityResultDTO lowerThan25Percent() {
        return TestCapacityResultDTO.builder()
                .charge(new BigDecimal("0.10"))
                .typeChargeApplyEnum(TypeChargeApplyEnum.DECREASE)
                .build();
    }

    public static TestCapacityResultDTO lowerThan50Percent() {
        return TestCapacityResultDTO.builder()
                .charge(new BigDecimal("0.0"))
                .typeChargeApplyEnum(TypeChargeApplyEnum.NONE)
                .build();
    }

    public static TestCapacityResultDTO lowerThan75Percent() {
        return TestCapacityResultDTO.builder()
                .charge(new BigDecimal("0.10"))
                .typeChargeApplyEnum(TypeChargeApplyEnum.INCREASE)
                .build();
    }

    public static TestCapacityResultDTO lowerThan100Percent() {
        return TestCapacityResultDTO.builder()
                .charge(new BigDecimal("0.25"))
                .typeChargeApplyEnum(TypeChargeApplyEnum.INCREASE)
                .build();
    }
}
