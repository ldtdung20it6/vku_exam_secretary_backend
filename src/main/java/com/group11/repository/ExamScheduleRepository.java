package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.models.ExamSchedule;


public interface ExamScheduleRepository extends JpaRepository<ExamSchedule,Integer>{
    
}
