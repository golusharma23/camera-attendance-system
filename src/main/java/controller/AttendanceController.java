package com.golu.cameraproof.controller;

import com.golu.cameraproof.model.Attendance;
import com.golu.cameraproof.repository.AttendanceRepository;
import com.golu.cameraproof.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Map<String, Object> payload) {
        String personId = (String) payload.get("personId");
        String role = (String) payload.get("role");
        int visibleMinutes = Integer.parseInt(payload.get("visibleMinutes").toString());
        int totalMinutes = Integer.parseInt(payload.get("totalMinutes").toString());

        String computedStatus = attendanceService.decideStatus(visibleMinutes, totalMinutes);

        Attendance attendance = new Attendance();
        attendance.setPersonId(personId);
        attendance.setRole(role);
        attendance.setStatus(computedStatus);

        return attendanceRepository.save(attendance);
    }

    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
