package com.br.park.api;

import com.br.park.api.dto.request.SpotStatusRequestDTO;
import com.br.park.api.dto.response.SpotStatusResponseDTO;
import com.br.park.services.SpotStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpotStatusController.class)
class SpotStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpotStatusService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarStatus200ComResponseValido() throws Exception {
        SpotStatusResponseDTO responseDTO = new SpotStatusResponseDTO();
        // Teste contrato da api

        when(service.findByLatLng(any())).thenReturn(responseDTO);

        SpotStatusRequestDTO requestDTO = new SpotStatusRequestDTO();
        requestDTO.setLat("1.23");
        requestDTO.setLng("4.56");

        mockMvc.perform(post("/spot-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
    }
}
