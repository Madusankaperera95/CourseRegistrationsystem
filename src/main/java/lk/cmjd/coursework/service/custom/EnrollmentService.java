package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.service.SuperService;

import java.util.ArrayList;

public interface EnrollmentService extends SuperService {
    String save(EnrollmentDto enrollmentDto) throws Exception;

    String update(EnrollmentDto enrollmentDto) throws Exception;

    ArrayList<EnrollmentDto> getAll() throws Exception;

    ArrayList<EnrollmentDto> getEnrollmentsByStudentId(String StudentId) throws Exception;

    String deleteEnrollment(int id);
}
