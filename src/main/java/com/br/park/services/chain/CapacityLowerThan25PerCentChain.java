package com.br.park.services.chain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.br.park.services.chain.TestCapacityResultFactory.lowerThan25Percent;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapacityLowerThan25PerCentChain implements CalculateChargeChain {

    private final CapacityLowerThan50PerCentChain chain;

    @Override
    public TestCapacityResultDTO evaluate(Integer percentOcuppancy) {

        log.info("Calculate charge charge per cent");

        if(percentOcuppancy <= 25) {
            log.info("Applying discount percent:{}%, occupancy:{}", 10, percentOcuppancy);
            return lowerThan25Percent();
        } else {
            return chain.evaluate(percentOcuppancy);
        }

    }
}
