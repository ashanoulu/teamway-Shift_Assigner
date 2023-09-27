package com.example.teamway.service;

import com.example.teamway.model.Worker;
import com.example.teamway.repository.ShiftRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkPlanningServiceTest {
    @InjectMocks
    private WorkPlanningService workPlanningService;

    @Mock
    private ShiftRepository shiftRepository;

    @Test
    public void testAssignShift_Success() {
        Worker worker = new Worker();
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 30, 8, 0);

        when(shiftRepository.existsByWorkerAndStartTimeBetween(eq(worker), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(false);

        boolean result = workPlanningService.assignShift(worker, startTime);
        assertTrue(result);
    }

    @Test
    public void testAssignShift_Failure() {
        Worker worker = new Worker();
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 30, 8, 0);

        when(shiftRepository.existsByWorkerAndStartTimeBetween(eq(worker), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(true);

        boolean result = workPlanningService.assignShift(worker, startTime);
        assertFalse(result);
    }
}

