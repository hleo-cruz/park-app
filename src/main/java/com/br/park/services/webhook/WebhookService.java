package com.br.park.services.webhook;

import com.br.park.api.dto.request.ParkLotRequestDTO;

public interface WebhookService {

    public void process(ParkLotRequestDTO paklot);
}
