package com.group11.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// public class ExamSession {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String sessionName;
//     private String sessionTitle;
//     private LocalDate sessionTime;
//     private int requiredSecretaries;
//     private LocalDate applicationDeadline;
    
//     @OneToMany(mappedBy = "examSession", cascade = CascadeType.ALL, orphanRemoval = true)
//     @JsonIgnore  
//     private List<ExamSchedule> examSchedules = new ArrayList<>();

//     @OneToMany(mappedBy = "examSession", cascade = CascadeType.ALL, orphanRemoval = true)
//     @JsonIgnore  
//     private List<SecretaryRequest> secretaryRequests = new ArrayList<>();
// }
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionName;
    private String sessionTitle;
    private LocalDate sessionTime;
    private int requiredSecretaries;
    private LocalDate applicationDeadline;
    
    @OneToMany(mappedBy = "examSession", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonIgnore
    @JsonManagedReference
    private List<ExamSchedule> examSchedules = new ArrayList<>();

    @OneToMany(mappedBy = "examSession", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    // @JsonIgnore  
    private List<SecretaryRequest> secretaryRequests = new ArrayList<>();
}
