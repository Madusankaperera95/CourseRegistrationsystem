package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.service.SuperService;
import lk.cmjd.coursework.util.Enums.SemesterTypes;

import java.util.ArrayList;

public interface EnrollmentService extends SuperService {
    String save(EnrollmentDto enrollmentDto) throws Exception;

    String update(EnrollmentDto enrollmentDto) throws Exception;

    ArrayList<EnrollmentDto> getAll() throws Exception;

    ArrayList<EnrollmentDto> getEnrollmentsByStudentId(String StudentId) throws Exception;

    ArrayList<EnrollmentDto> getEnrollmentsForFilter(String CourseId, SemesterTypes semseter) throws Exception;

    String deleteEnrollment(int id);
}
