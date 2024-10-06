package com.group11.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group11.models.ExamSchedule;
import com.group11.models.User;
import com.group11.repository.ExamScheduleRepository;

@Service
public class ExamScheduleServiceImp implements ExamScheduleService {

    @Autowired
    private ExamScheduleRepository examScheduleRepository;

    @Autowired
    private UserService userService;

    // @Override
    // public ExamSchedule createNewExamSchedule(ExamSchedule reqExamScheduleBody, Integer userId) throws Exception {

    //     ExamSchedule examSchedule = new ExamSchedule();
    //     examSchedule.setSerialNumber(reqExamScheduleBody.getSerialNumber());
    //     examSchedule.setCourseName(reqExamScheduleBody.getCourseName());
    //     examSchedule.setCourseClassName(reqExamScheduleBody.getCourseClassName());
    //     examSchedule.setExamFormat(reqExamScheduleBody.getExamFormat());
    //     examSchedule.setTimeToEnterExamRoom(reqExamScheduleBody.getTimeToEnterExamRoom());
    //     examSchedule.setExamDuration(reqExamScheduleBody.getExamDuration());
    //     examSchedule.setExamDate(reqExamScheduleBody.getExamDate());
    //     examSchedule.setExamRoom(reqExamScheduleBody.getExamRoom());
    //     examSchedule.setInstructor(reqExamScheduleBody.getInstructor());
    //     examSchedule.setExamList(reqExamScheduleBody.getExamList());
    //     examSchedule.setStudentsAbsent(reqExamScheduleBody.getStudentsAbsent());
    //     examSchedule.setExamProctor1(reqExamScheduleBody.getExamProctor1());
    //     examSchedule.setExamProctor2(reqExamScheduleBody.getExamProctor2());
    //     examSchedule.setExamSituation(reqExamScheduleBody.getExamSituation());
    //     examSchedule.setCourseCode(reqExamScheduleBody.getCourseCode());
    //     examSchedule.setNotes(reqExamScheduleBody.getNotes());
    //     examSchedule.setExamSecretary(reqExamScheduleBody.getExamSecretary());
    //     examSchedule.setItRoomSupervisor(reqExamScheduleBody.getItRoomSupervisor());
    //     examSchedule.setSoftwareSystemSupervisor(reqExamScheduleBody.getSoftwareSystemSupervisor());
    //     examSchedule.setRemarks(reqExamScheduleBody.getRemarks());

    //     return examScheduleRepository.save(examSchedule);
    // }

    @Override
    public ExamSchedule createNewExamSchedule(ExamSchedule reqExamScheduleBody, Integer userId) throws Exception {
    
        ExamSchedule examSchedule = new ExamSchedule();
        examSchedule.setSerialNumber(reqExamScheduleBody.getSerialNumber());
        examSchedule.setCourseName(reqExamScheduleBody.getCourseName());
        examSchedule.setCourseClassName(reqExamScheduleBody.getCourseClassName());
        examSchedule.setCredit(reqExamScheduleBody.getCredit()); // Tín chỉ
        examSchedule.setHours(reqExamScheduleBody.getHours()); // Số tiết (theo số TC)
        examSchedule.setWeight(reqExamScheduleBody.getWeight()); // Trọng số (theo Đề cương CT)
        examSchedule.setExamFormat(reqExamScheduleBody.getExamFormat());
        examSchedule.setTimeToEnterExamRoom(reqExamScheduleBody.getTimeToEnterExamRoom());
        examSchedule.setExamDuration(reqExamScheduleBody.getExamDuration()); // Thời gian thi
        examSchedule.setExamDate(reqExamScheduleBody.getExamDate()); // Ngày thi
        examSchedule.setExamDurationPerStudent(reqExamScheduleBody.getExamDurationPerStudent()); // Thời gian làm bài
        examSchedule.setExamRoom(reqExamScheduleBody.getExamRoom());
        examSchedule.setInstructor(reqExamScheduleBody.getInstructor());
        examSchedule.setExamList(reqExamScheduleBody.getExamList());
        examSchedule.setTeachingGroup(reqExamScheduleBody.getTeachingGroup()); // Nhóm CB giảng dạy
        examSchedule.setCourseLeader(reqExamScheduleBody.getCourseLeader()); // Trưởng nhóm Học phần
        examSchedule.setSpecialistGroup(reqExamScheduleBody.getSpecialistGroup()); // Nhóm CB có chuyên môn coi & chấm thi Vấn đáp
        examSchedule.setTotalClasses(reqExamScheduleBody.getTotalClasses()); // Tổng số lớp mỗi HP
        examSchedule.setStudentsPerClass(reqExamScheduleBody.getStudentsPerClass()); // SL SV/Lớp
        examSchedule.setStudentsExcess(reqExamScheduleBody.getStudentsExcess()); // SV Dư
        examSchedule.setStudentsPerRoom(reqExamScheduleBody.getStudentsPerRoom()); // SV/Phòng
        examSchedule.setNotes(reqExamScheduleBody.getNotes());
        examSchedule.setTotalStudents(reqExamScheduleBody.getTotalStudents()); // Tồng SV/HP
        examSchedule.setProctor1(reqExamScheduleBody.getProctor1()); // Cán Bộ Coi thi 1
        examSchedule.setProctor2(reqExamScheduleBody.getProctor2()); // Cán Bộ Coi thi 2
        examSchedule.setAllocation(reqExamScheduleBody.getAllocation()); // Phân bổ cho các đơn vị
        examSchedule.setExamSituation(reqExamScheduleBody.getExamSituation());
        examSchedule.setCourseCode(reqExamScheduleBody.getCourseCode()); // Khóa
        examSchedule.setAdditionalNotes(reqExamScheduleBody.getAdditionalNotes()); // Lưu ý
        examSchedule.setExamSecretary(reqExamScheduleBody.getExamSecretary());
        examSchedule.setRoomSupervisor(reqExamScheduleBody.getRoomSupervisor()); // CB Giám sát phòng máy
        examSchedule.setSoftwareSupervisor(reqExamScheduleBody.getSoftwareSupervisor()); // CB Trực hệ thống Phần mềm
        examSchedule.setRemarks(reqExamScheduleBody.getRemarks());
    
        return examScheduleRepository.save(examSchedule);
    }
    


    @Override
    public String deleteExamSchedule(Integer examScheduleId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Optional<ExamSchedule> examScheduleOptional = examScheduleRepository.findById(examScheduleId);
        if (examScheduleOptional.isPresent()) {
            if (user != null && "admin".equalsIgnoreCase(user.getRole())) {
                examScheduleRepository.delete(examScheduleOptional.get());
                return "Exam schedule deleted successfully.";
            } else {
                return "User does not have the required permissions.";
            }
        } else {
            return "Exam schedule not found.";
        }
    }

    @Override
    public List<ExamSchedule> findAllExamSchedule() {
        return examScheduleRepository.findAll();
    }

}
