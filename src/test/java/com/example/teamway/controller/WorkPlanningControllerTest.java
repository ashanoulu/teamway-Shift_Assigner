package com.example.teamway.controller;

import com.example.teamway.model.Worker;
import com.example.teamway.repository.WorkerRepository;
import com.example.teamway.request.ShiftRequest;
import com.example.teamway.service.WorkPlanningService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkPlanningController.class)
class WorkPlanningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerRepository workerRepository;

    @MockBean
    private WorkPlanningService workPlanningService;

    @Test
    public void testAssignShift_Success() throws Exception {
        ShiftRequest validRequest = new ShiftRequest();
        validRequest.setWorkerId(1L);

        String dateString = "2018-02-05T08:00:00.332Z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        System.out.println(dateTime);
        validRequest.setStartTime(dateTime);

        Worker worker = new Worker();
        worker.setId(1L);

        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));

        when(workPlanningService.assignShift(worker, validRequest.getStartTime())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/assign-shift")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Shift assigned successfully"));

        verify(workerRepository, times(1)).findById(1L);
        verify(workPlanningService, times(1)).assignShift(worker, validRequest.getStartTime());
    }


    @Test
    public void testAssignShift_AssignmentFailed() throws Exception {
        ShiftRequest validRequest = new ShiftRequest();
        validRequest.setWorkerId(1L);
        validRequest.setStartTime(LocalDateTime.now());

        Worker worker = new Worker();
        worker.setId(1L);

        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));

        when(workPlanningService.assignShift(worker, validRequest.getStartTime())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/assign-shift")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validRequest)))
                .andExpect(status().isBadRequest());

    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }
}