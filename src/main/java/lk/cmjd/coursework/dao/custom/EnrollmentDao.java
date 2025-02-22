package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import org.hibernate.Session;

import java.util.ArrayList;

public interface EnrollmentDao extends SuperDao {

    String save(EnrollmentEntity enrollmentEntity, Session session);

    String update(EnrollmentEntity enrollmentEntity,Session session);

    EnrollmentEntity getEnrollment(int id);

    boolean checkAlreadyRegistered(SemesterTypes semesterType, String CourseId, String StudentId, Session session);

    boolean SemesterOneHasCompleted(String CourseId, String StudentId, Session session);

    boolean semesterHasCompleted(int id,Session session);

    String delete(int id,Session session);

    ArrayList<EnrollmentEntity> getAll() throws Exception;

    ArrayList<EnrollmentEntity> getEnrollmentsByStudentId(String enrollId,Session session);

    ArrayList<EnrollmentEntity> getEnrollmentsForFilter(String courseId,SemesterTypes semesterType,Session session);

}
