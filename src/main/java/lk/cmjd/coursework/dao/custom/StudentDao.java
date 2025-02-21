package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.entity.CourseEntity;
import lk.cmjd.coursework.entity.StudentEntity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface StudentDao extends SuperDao {
    String Save(StudentEntity student) throws Exception;

    ArrayList<StudentEntity> getAll();

    String Update(StudentEntity student,Session session) throws Exception;

    StudentEntity getStudent(String id, Session session);

    StudentEntity getStudentByEmail(String email,Session session);

    List<StudentEntity> searchStudentByIdOrEmail(String value, Session session);

}
