package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.models.ExamSession;

public interface ExamSessionRepository extends JpaRepository<ExamSession,Integer>{
    
}
