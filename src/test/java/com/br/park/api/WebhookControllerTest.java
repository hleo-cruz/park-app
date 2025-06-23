package com.br.park.api;

import com.br.park.api.dto.request.ParkLotRequestDTO;
import com.br.park.services.webhook.EntryParkLotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebhookController.class)
class WebhookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EntryParkLotService entryParkLotService; // mock real do bean a ser retornado

    @Test
    void deveExecutarServicoDinamicoBaseadoNoEventType() throws Exception {
        ParkLotRequestDTO dto = new ParkLotRequestDTO();
        dto.setEventType(EventTypeEnum.ENTRY_PARK_LOT);

        when(applicationContext.getBean(EntryParkLotService.class)).thenReturn(entryParkLotService);

        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(entryParkLotService).process(dto);
    }
}



