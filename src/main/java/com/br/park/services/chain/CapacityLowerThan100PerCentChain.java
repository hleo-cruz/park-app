package com.br.park.services.chain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.br.park.services.chain.TestCapacityResultFactory.lowerThan100Percent;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapacityLowerThan100PerCentChain implements CalculateChargeChain{

    @Override
    public TestCapacityResultDTO evaluate(Integer percentOcuppancy) {
        log.info("Applying increase percent:{}% occupancy:{}", 25, percentOcuppancy);
        return lowerThan100Percent();
    }
}
