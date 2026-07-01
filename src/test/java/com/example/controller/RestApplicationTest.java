package com.example.controller;

import com.example.config.ConfigurationManagement;
import com.example.service.CpuBurnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
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

    @Test
    void shouldStartCpuLoadWhenCpuloadEndpointIsRequested() throws Exception {
        mockMvc.perform(get("/cpuload").param("cpu", "120"))
                .andExpect(status().isOk())
                .andExpect(content().string("CPU load started"));

        verify(cpuBurnerService).burnCpu(120);
    }
}
