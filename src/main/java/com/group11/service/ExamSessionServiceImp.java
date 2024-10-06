package com.group11.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group11.models.ExamSchedule;
import com.group11.models.ExamSession;
import com.group11.models.SecretaryRequest;
import com.group11.repository.ExamScheduleRepository;
import com.group11.repository.ExamSessionRepository;
import com.group11.repository.SecretaryRequestRepository;

@Service
public class ExamSessionServiceImp implements ExamSessionService {

    // @Autowired
    // private UserService userService;

    // @Autowired
    // private ExamScheduleService examScheduleService;

    @Autowired
    private ExamSessionRepository examSessionRepository;

    @Autowired
    private SecretaryRequestRepository secretaryRequestRepository;

    @Autowired
    private ExamScheduleRepository examScheduleRepository;

    @Override
    public ExamSession createExamSession(ExamSession reqExamSessionBody, Integer userId) throws Exception {

        ExamSession examSession = new ExamSession();
        examSession.setSessionName(reqExamSessionBody.getSessionName());
        examSession.setSessionTitle(reqExamSessionBody.getSessionTitle());
        examSession.setSessionTime(reqExamSessionBody.getSessionTime());
        examSession.setRequiredSecretaries(reqExamSessionBody.getRequiredSecretaries());
        examSession.setApplicationDeadline(reqExamSessionBody.getApplicationDeadline());

        List<ExamSchedule> examSchedules = reqExamSessionBody.getExamSchedules();
        for (ExamSchedule examSchedule : examSchedules) {
            examSchedule.setExamSession(examSession);
        }
        examSession.setExamSchedules(examSchedules);
        return examSessionRepository.save(examSession);
    }

    @Override
    public List<ExamSession> findAllExamSession() {
        return examSessionRepository.findAll();
    }

    @Override
    public String deleteExamSession(Integer userId, Integer examSessionId) throws Exception {
        Optional<ExamSession> examSessionOptional = examSessionRepository.findById(examSessionId);
        if (examSessionOptional.isPresent()) {
            ExamSession examSession = examSessionOptional.get();
            examSessionRepository.delete(examSession);
            return "Exam session deleted successfully.";
        } else {
            return "Exam session not found.";
        }
    }

    @Override
    public SecretaryRequest registerSecretary(SecretaryRequest request, int examSessionId) throws Exception {
        ExamSession examSession = examSessionRepository.findById(examSessionId)
                .orElseThrow(() -> new Exception("ExamSession not found"));
        if (examSession.getSecretaryRequests().size() < examSession.getRequiredSecretaries()) {
            request.setExamSession(examSession);
            examSession.getSecretaryRequests().add(request);
            return secretaryRequestRepository.save(request);
        } else {
            throw new Exception("Registration limit reached");
        }
    }

    @Override
    public void assignSecretaryToSchedule(int secretaryId, int examScheduleId) throws Exception {
        SecretaryRequest secretary = secretaryRequestRepository.findById(secretaryId)
                .orElseThrow(() -> new Exception("Secretary not found"));
        ExamSchedule schedule = examScheduleRepository.findById(examScheduleId)
                .orElseThrow(() -> new Exception("ExamSchedule not found"));

        schedule.setExamSecretary(secretary.getSecretaryName());
        secretary.setAssigned(true);
        examScheduleRepository.save(schedule);
    }

}
