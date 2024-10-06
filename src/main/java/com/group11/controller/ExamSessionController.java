package com.group11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group11.config.JwtConstant;
import com.group11.models.ExamSession;
import com.group11.models.SecretaryRequest;
import com.group11.response.ApiResponse;
import com.group11.service.ExamSessionService;
import com.group11.service.UserService;

@RestController
@RequestMapping("api/exam-session")
public class ExamSessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamSessionService examSessionService;

    @GetMapping("")
    public List<ExamSession> getListExamSession() {
        List<ExamSession> examSession = examSessionService.findAllExamSession();
        return examSession;
    }

    @PostMapping("")
    public ResponseEntity<ExamSession> createExamSession(@RequestHeader(JwtConstant.JWT_HEADER) String jwt, @RequestBody ExamSession reqExamSessionBody) throws Exception {
        int userId = userService.findUserFromJwt(jwt).getId();
        ExamSession examSession = examSessionService.createExamSession(reqExamSessionBody, userId);
        return new ResponseEntity<>(examSession, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-{examSessionId}")
    public ResponseEntity<ApiResponse> deleteExamSession(@RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @PathVariable Integer examSessionId) throws Exception {
        int userId = userService.findUserFromJwt(jwt).getId();
        String message = examSessionService.deleteExamSession(examSessionId, userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/{examSessionId}/register-secretary")
    public ResponseEntity<SecretaryRequest> registerSecretary(
        @RequestBody SecretaryRequest request, @PathVariable int examSessionId) {
        try {
            SecretaryRequest newRequest = examSessionService.registerSecretary(request, examSessionId);
            return ResponseEntity.ok(newRequest);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/assign-secretary/{secretaryId}/{examScheduleId}")
    public ResponseEntity<String> assignSecretaryToSchedule(
        @PathVariable int secretaryId, @PathVariable int examScheduleId) {
        try {
            examSessionService.assignSecretaryToSchedule(secretaryId, examScheduleId);
            return ResponseEntity.ok("Secretary assigned successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
