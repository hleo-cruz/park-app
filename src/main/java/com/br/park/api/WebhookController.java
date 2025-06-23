package com.br.park.api;

import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.services.webhook.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final ApplicationContext applicationContext;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void processEvent(@RequestBody ParkLotRequestDTO dto) {

        Class<? extends WebhookService> clazz = dto.getEventType().getClazz();
        WebhookService service = applicationContext.getBean(clazz);
        service.process(dto);

    }
}
