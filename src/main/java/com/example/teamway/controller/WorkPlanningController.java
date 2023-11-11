package com.example.teamway.controller;

import com.example.teamway.model.Shift;
import com.example.teamway.model.Worker;
import com.example.teamway.repository.WorkerRepository;
import com.example.teamway.request.ShiftRequest;
import com.example.teamway.service.WorkPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/")
public class WorkPlanningController {
    @Autowired
    private WorkPlanningService workPlanningService;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/shifts")
    public List<Shift> getShifts(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                 @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return workPlanningService.getShiftsByDateRange(start, end);
    }

    @PostMapping("/assign-shift")
    public ResponseEntity<String> assignShift(@Valid @RequestBody ShiftRequest request) {
        Worker worker = workerRepository.findById(request.getWorkerId()).orElse(null);
        if (worker == null) {
            return ResponseEntity.badRequest().body("Worker not found");
        }

        if (workPlanningService.assignShift(worker, request.getStartTime())) {
            return ResponseEntity.ok("Shift assigned successfully");
        } else {
            return ResponseEntity.badRequest().body("Shift assignment failed");
        }
    }
}

