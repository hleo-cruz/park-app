package com.br.park.api;

import com.br.park.api.dto.request.RevenueRequestDTO;
import com.br.park.api.dto.response.RevenueResponseDTO;
import com.br.park.services.RevenueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RevenueController.class)
class RevenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevenueService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornar200ComRespostaValida() throws Exception {
        RevenueRequestDTO request = new RevenueRequestDTO();
        request.setDate(LocalDate.of(2024, 5, 1));
        request.setSector("SEC01");

        RevenueResponseDTO response = new RevenueResponseDTO();
        when(service.findByDateAndSector(any())).thenReturn(response);

        mockMvc.perform(post("/revenue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
