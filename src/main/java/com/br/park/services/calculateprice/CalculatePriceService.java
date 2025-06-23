package com.br.park.services.calculateprice;

import com.br.park.infrastructure.repository.parklot.TypeChargeApplyEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CalculatePriceService {

    public BigDecimal calculatePrice(CalculatePriceDTO calculatePrice) {

        LocalDateTime entryTime = calculatePrice.getEntryTime();
        LocalDateTime exitTime = calculatePrice.getExitTime();
        Long differenceInMinutes = findDifferenceInMinutes(entryTime, exitTime);

        BigDecimal charge = calculatePrice.getCharge();
        BigDecimal basePrice = calculatePrice.getBasePrice();
        Integer durationLimitMinutes = calculatePrice.getDurationLimitMinutes();

        TypeChargeApplyEnum typeChargeApply = calculatePrice.getTypeChargeApply();
        BigDecimal pricePerMinute = findPricePerMinute(basePrice, durationLimitMinutes);
        BigDecimal priceWithoutCharge = findPriceWithoutCharge(pricePerMinute, differenceInMinutes);
        BigDecimal chargePrice = findChargePrice(priceWithoutCharge, charge);

        return typeChargeApply.isIncrease() ? priceWithoutCharge.add(chargePrice) : priceWithoutCharge.subtract(chargePrice);
    }

    private BigDecimal findPricePerMinute(BigDecimal basePrice, Integer limitDurationInMinutes) {
        return new BigDecimal(limitDurationInMinutes).divide(basePrice, RoundingMode.UP);
    }

    private Long findDifferenceInMinutes(LocalDateTime entryTime, LocalDateTime exitTime) {
        return Duration.between(entryTime, exitTime).toMinutes();
    }

    private BigDecimal findPriceWithoutCharge(BigDecimal basePrice, Long durationInMinutes) {
        return basePrice.multiply(new BigDecimal(durationInMinutes));
    }

    private BigDecimal findChargePrice(BigDecimal priceWithoutCharge, BigDecimal charge) {
        return priceWithoutCharge.multiply(charge).divide(BigDecimal.valueOf(100), 2, RoundingMode.UP);
    }
}
