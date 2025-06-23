package com.br.park.infrastructure.repository.parklot;

public enum TypeChargeApplyEnum {
    INCREASE,
    DECREASE,
    NONE;

    public Boolean isIncrease() {
        return this == INCREASE;
    }
}
