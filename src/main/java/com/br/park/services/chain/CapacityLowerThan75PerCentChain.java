package com.br.park.services.chain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.br.park.services.chain.TestCapacityResultFactory.lowerThan75Percent;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapacityLowerThan75PerCentChain implements CalculateChargeChain{

    private final CapacityLowerThan100PerCentChain chain;

    @Override
    public TestCapacityResultDTO evaluate(Integer percentOcuppancy) {

        if(percentOcuppancy <= 75) {
            log.info("Applying increase percent:{}% occupancy:{}", 10, percentOcuppancy);
            return lowerThan75Percent();
        } else {
            return chain.evaluate(percentOcuppancy);
        }

    }
}
