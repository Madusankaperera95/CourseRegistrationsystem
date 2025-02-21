package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.StudentDto;
import lk.cmjd.coursework.entity.StudentEntity;
import lk.cmjd.coursework.service.SuperService;

import java.util.ArrayList;

public interface StudentService extends SuperService {

    String save(StudentDto studentDto) throws Exception;

    ArrayList<StudentDto> getAll() throws Exception;

    String update(StudentDto studentDto) throws Exception;

    StudentDto getStudent(String email);

    ArrayList<StudentDto> searchStudentByIdOrEmail(String value);
}
