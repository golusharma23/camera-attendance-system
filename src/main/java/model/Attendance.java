package com.golu.cameraproof.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Attendance {

    @Id
    @GeneratedValue
    private Long id;

    private String personId;   // student / faculty id
    private String role;       // STUDENT / FACULTY
    private String status;     // PRESENT / LATE / ABSENT

    public Long getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
