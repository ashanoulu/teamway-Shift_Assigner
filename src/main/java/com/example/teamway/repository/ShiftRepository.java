package com.example.teamway.repository;

import com.example.teamway.model.Shift;
import com.example.teamway.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByWorkerAndStartTimeBetween(Worker worker, LocalDateTime start, LocalDateTime end);
}
