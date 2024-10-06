package com.group11.service;

import java.util.List;

import com.group11.models.ExamSchedule;

public interface ExamScheduleService {
    
    ExamSchedule createNewExamSchedule(ExamSchedule reqExamScheduleBody,Integer userId) throws Exception;

    String deleteExamSchedule(Integer examScheduleId,Integer userId) throws Exception;

    List<ExamSchedule> findAllExamSchedule();
}
