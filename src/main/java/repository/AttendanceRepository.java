package com.golu.cameraproof.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.golu.cameraproof.model.Attendance;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {
}
