package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.service.SuperService;

public interface EnrollmentService extends SuperService {
    String save(EnrollmentDto enrollmentDto) throws Exception;
}
