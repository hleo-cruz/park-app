package com.br.park.services.chain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.br.park.services.chain.TestCapacityResultFactory.lowerThan50Percent;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapacityLowerThan50PerCentChain implements CalculateChargeChain{

    private final CapacityLowerThan75PerCentChain chain;

    @Override
    public TestCapacityResultDTO evaluate(Integer percentOcuppancy) {

        if(percentOcuppancy <= 50) {
            log.info("There is no discount occupancy:{}", percentOcuppancy);
            return lowerThan50Percent();
        } else {
            return chain.evaluate(percentOcuppancy);
        }

    }
}
