package com.br.park.api;

import com.br.park.api.dto.request.LicensePlateStatusRequestDTO;
import com.br.park.api.dto.response.LicensePlateStatusResponseDTO;
import com.br.park.services.LicensePlateStatusService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LicensePlateStatusController.class)
class LicensePlateStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LicensePlateStatusService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornar200ComRespostaValida() throws Exception {
        LicensePlateStatusRequestDTO request = new LicensePlateStatusRequestDTO();
        request.setLicensePlate("ABC1234");

        LicensePlateStatusResponseDTO response = new LicensePlateStatusResponseDTO();
        when(service.findByLicensePlate(any())).thenReturn(response);

        mockMvc.perform(post("/plate-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
