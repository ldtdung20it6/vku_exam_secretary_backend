package com.group11.service;

import java.util.List;

import com.group11.models.ExamSession;
import com.group11.models.SecretaryRequest;

public interface ExamSessionService {

    ExamSession createExamSession(ExamSession examSession, Integer userId) throws Exception;

    List<ExamSession> findAllExamSession();

    String deleteExamSession(Integer userId, Integer examSessionId) throws Exception;

    SecretaryRequest registerSecretary(SecretaryRequest request, int examSessionId) throws Exception;

    void assignSecretaryToSchedule(int secretaryId, int examScheduleId) throws Exception;
}
