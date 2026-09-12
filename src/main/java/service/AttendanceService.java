package com.golu.cameraproof.service;

import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    public String decideStatus(int visibleMinutes, int totalMinutes) {

        if (visibleMinutes >= totalMinutes * 0.6)
            return "PRESENT";
        else if (visibleMinutes >= totalMinutes * 0.3)
            return "LATE";
        else
            return "ABSENT";
    }
}
