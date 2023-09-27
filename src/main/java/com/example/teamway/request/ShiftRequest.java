package com.example.teamway.request;

import com.example.teamway.validation.ValidShiftTime;

import java.time.LocalDateTime;

public class ShiftRequest {


    private Long workerId;

    @ValidShiftTime
    private LocalDateTime startTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

}

