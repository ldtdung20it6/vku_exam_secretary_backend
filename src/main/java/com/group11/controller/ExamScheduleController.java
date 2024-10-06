package com.group11.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group11.config.JwtConstant;
import com.group11.models.ExamSchedule;
import com.group11.response.ApiResponse;
import com.group11.service.ExamScheduleService;
import com.group11.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/exam-schedule")
public class ExamScheduleController {

    @Autowired
    private ExamScheduleService examScheduleService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<ExamSchedule> getListExamSchedule() {
        List<ExamSchedule> examSchedules = examScheduleService.findAllExamSchedule();
        return examSchedules;
    }

    @PostMapping("")
    public ResponseEntity<ExamSchedule> createExamSchedule(@RequestHeader(JwtConstant.JWT_HEADER) String jwt, @RequestBody ExamSchedule reqExamScheduleBody) throws Exception {
        int userId = userService.findUserFromJwt(jwt).getId();
        ExamSchedule examSchedule = examScheduleService.createNewExamSchedule(reqExamScheduleBody, userId);
        return new ResponseEntity<>(examSchedule, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-{examScheduleId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @PathVariable Integer examScheduleId) throws Exception {
        int userId = userService.findUserFromJwt(jwt).getId();
        String message = examScheduleService.deleteExamSchedule(examScheduleId, userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    
}
