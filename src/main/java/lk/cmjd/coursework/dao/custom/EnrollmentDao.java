package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import org.hibernate.Session;

public interface EnrollmentDao extends SuperDao {

    String save(EnrollmentEntity enrollmentEntity, Session session);

    boolean checkAlreadyRegistered(SemesterTypes semesterType, String CourseId, String StudentId, Session session);

    boolean SemesterOneHasCompleted(String CourseId, String StudentId, Session session);

}
