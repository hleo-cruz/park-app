package com.br.park.services.webhook;


import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.infrastructure.repository.parklot.ParkLot;
import com.br.park.mapper.ParkLotMapper;
import com.br.park.services.ParkLotService;
import com.br.park.services.SpotService;
import com.br.park.services.chain.CapacityLowerThan25PerCentChain;
import com.br.park.services.chain.TestCapacityResultDTO;
import com.br.park.services.chain.TestCapacityResultFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryParkLotService implements WebhookService {

    private final ParkLotService service;
    private final SpotService spotService;
    private final CapacityLowerThan25PerCentChain chain;

    @Override
    public void process(ParkLotRequestDTO paklot) {

        ParkLot parkLot = ParkLotMapper.toEntity(paklot);
        Integer countSpot = spotService.countPercentAvailableSpots();

        TestCapacityResultDTO testCapacityResult = chain.evaluate(countSpot);
        parkLot.setAmountCharge(testCapacityResult.getCharge());
        parkLot.setTypeChargeApply(testCapacityResult.getTypeChargeApplyEnum());

        service.save(parkLot);

    }


}