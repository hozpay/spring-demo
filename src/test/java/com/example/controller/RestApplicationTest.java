package com.example.controller;

import com.example.config.ConfigurationManagement;
import com.example.service.CpuBurnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestApplication.class)
class RestApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigurationManagement config;

    @MockBean
    private CpuBurnerService cpuBurnerService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void shouldStartCpuLoadWhenCpuloadEndpointIsRequested() throws Exception {
        mockMvc.perform(get("/cpuload").param("cpu", "120"))
                .andExpect(status().isOk())
                .andExpect(content().string("CPU load started"));

        verify(cpuBurnerService).burnCpu(120);
    }

    @Test
    void shouldProxyRemoteResponseWhenRemoteEndpointIsRequested() throws Exception {
        String remoteUrl = "https://httpstat.us/504?sleep=60000";
        ResponseEntity<String> remoteResponse = ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("gateway timeout");

        when(restTemplate.exchange(eq(remoteUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(remoteResponse);

        mockMvc.perform(get("/remote").param("url", remoteUrl))
                .andExpect(status().isGatewayTimeout())
                .andExpect(content().string("gateway timeout"));

        verify(restTemplate).exchange(eq(remoteUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
    }
}
