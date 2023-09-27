package com.example.teamway.service;

import com.example.teamway.model.Shift;
import com.example.teamway.model.Worker;
import com.example.teamway.repository.ShiftRepository;
import com.example.teamway.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkPlanningService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getShiftsByDateRange(LocalDateTime start, LocalDateTime end) {
        return shiftRepository.findByStartTimeBetween(start, end);
    }

    public boolean assignShift(Worker worker, LocalDateTime startTime) {
        LocalDateTime endTime = startTime.plusHours(8);
        if (shiftRepository.existsByWorkerAndStartTimeBetween(worker, startTime, endTime)) {
            return false; // Worker already has a shift on this day
        }

        Shift shift = new Shift();
        shift.setWorker(worker);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shiftRepository.save(shift);
        return true;
    }
}
