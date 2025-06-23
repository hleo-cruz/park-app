package com.br.park.api;

import com.br.park.infrastructure.repository.parklot.ParkLotStatusEnum;
import com.br.park.services.webhook.EntryParkLotService;
import com.br.park.services.webhook.EntrySpotService;
import com.br.park.services.webhook.LeaveParkLotService;
import com.br.park.services.webhook.WebhookService;

public enum EventTypeEnum {

    ENTRY_PARK_LOT(EntryParkLotService.class, ParkLotStatusEnum.ACTIVE),
    ENTRY_SPOT(EntrySpotService.class, ParkLotStatusEnum.PARKED),
    LEAVE_PARK_LOT(LeaveParkLotService.class, ParkLotStatusEnum.EXITED),;

    private Class< ? extends WebhookService> clazz;
    private ParkLotStatusEnum status;

    EventTypeEnum(Class<? extends WebhookService> clazz, ParkLotStatusEnum status) {
        this.clazz = clazz;
        this.status = status;
    }

    public Class<? extends WebhookService> getClazz() {
        return clazz;
    }

    public ParkLotStatusEnum getStatus() {
        return status;
    }
}
