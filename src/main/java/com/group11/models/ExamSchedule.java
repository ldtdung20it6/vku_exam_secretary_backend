package com.group11.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// public class ExamSchedule {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;
    
//     private Integer serialNumber; // Số thứ tự có thể null
    
//     private String courseName; // Tên khóa học có thể null
    
//     private String courseClassName; // Tên lớp học có thể null
    
//     private String credit; // Tín chỉ có thể null
    
//     private String hours; // Số tiết (theo số TC) có thể null
    
//     private String weight; // Trọng số (theo Đề cương CT) có thể null
    
//     private String examFormat; // Định dạng bài thi có thể null
    
//     private String timeToEnterExamRoom; // Thời gian vào phòng thi có thể null
    
//     private String examDuration; // Thời gian thi có thể null
    
//     private String examDate; // Ngày thi có thể null
    
//     private String examDurationPerStudent; // Thời gian làm bài có thể null
    
//     private String examRoom; // Phòng thi có thể null
    
//     private String instructor; // Giảng viên có thể null
    
//     private String examList; // Danh sách bài thi có thể null
    
//     private String teachingGroup; // Nhóm CB giảng dạy có thể null
    
//     private String courseLeader; // Trưởng nhóm Học phần có thể null
    
//     private String specialistGroup; // Nhóm CB có chuyên môn coi & chấm thi Vấn đáp có thể null
    
//     private String totalClasses; // Tổng số lớp mỗi HP có thể null
    
//     private String studentsPerClass; // SL SV/Lớp có thể null
    
//     private String studentsExcess; // SV Dư có thể null
    
//     private String studentsPerRoom; // SV/Phòng có thể null
    
//     private String notes; // Ghi chú có thể null
    
//     private String totalStudents; // Tồng SV/HP có thể null
    
//     private String proctor1; // Cán Bộ Coi thi 1 có thể null
    
//     private String proctor2; // Cán Bộ Coi thi 2 có thể null
    
//     private String allocation; // Phân bổ cho các đơn vị có thể null
    
//     private String examSituation; // Tình hình thi có thể null
    
//     private String courseCode; // Khóa có thể null
    
//     private String additionalNotes; // Lưu ý có thể null
    
//     private String examSecretary; // Thư ký thi có thể null
    
//     private String roomSupervisor; // CB Giám sát phòng máy có thể null
    
//     private String softwareSupervisor; // CB Trực hệ thống Phần mềm có thể null
    
//     private String remarks; // Nhận xét có thể null

//     @JsonIgnore
//     @ManyToOne
//     @JoinColumn(name = "exam_session_id", nullable = false)
//     private ExamSession examSession;
// }
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Integer serialNumber; // Số thứ tự có thể null
    private String courseName; // Tên khóa học có thể null
    private String courseClassName; // Tên lớp học có thể null
    private String credit; // Tín chỉ có thể null
    private String hours; // Số tiết có thể null
    private String weight; // Trọng số có thể null
    private String examFormat; // Định dạng bài thi có thể null
    private String timeToEnterExamRoom; // Thời gian vào phòng thi có thể null
    private String examDuration; // Thời gian thi có thể null
    private String examDate; // Ngày thi có thể null
    private String examDurationPerStudent; // Thời gian làm bài có thể null
    private String examRoom; // Phòng thi có thể null
    private String instructor; // Giảng viên có thể null
    private String examList; // Danh sách bài thi có thể null
    private String teachingGroup; // Nhóm CB giảng dạy có thể null
    private String courseLeader; // Trưởng nhóm Học phần có thể null
    private String specialistGroup; // Nhóm CB có chuyên môn có thể null
    private String totalClasses; // Tổng số lớp có thể null
    private String studentsPerClass; // SL SV/Lớp có thể null
    private String studentsExcess; // SV Dư có thể null
    private String studentsPerRoom; // SV/Phòng có thể null
    private String notes; // Ghi chú có thể null
    private String totalStudents; // Tổng SV có thể null
    private String proctor1; // Cán Bộ Coi thi 1 có thể null
    private String proctor2; // Cán Bộ Coi thi 2 có thể null
    private String allocation; // Phân bổ có thể null
    private String examSituation; // Tình hình thi có thể null
    private String courseCode; // Khóa có thể null
    private String additionalNotes; // Lưu ý có thể null
    private String examSecretary; // Thư ký thi có thể null
    private String roomSupervisor; // CB Giám sát có thể null
    private String softwareSupervisor; // CB Trực hệ thống có thể null
    private String remarks; // Nhận xét có thể null

    // @JsonIgnore
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "exam_session_id", nullable = false)
    private ExamSession examSession;
}
