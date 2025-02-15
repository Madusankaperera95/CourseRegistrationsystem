package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.EnrollmentDao;
import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnrollmentDaoImpl implements EnrollmentDao {
    @Override
    public String save(EnrollmentEntity enrollmentEntity,Session session) {
        session.save(enrollmentEntity);  // Save entity
        return "ok";
    }

    @Override
    public boolean checkAlreadyRegistered(SemesterTypes semesterType, String CourseId, String StudentId, Session session) {
        EnrollmentEntity existingEnrollment = session.createQuery("FROM EnrollmentEntity WHERE courseId.courseId = :CourseId AND semesterType = :semesterType AND studentEntity.StudentId = :StudentId", EnrollmentEntity.class)
                .setParameter("CourseId", CourseId)
                .setParameter("semesterType",semesterType)
                .setParameter("StudentId",StudentId)
                .uniqueResult();

        return existingEnrollment != null;
    }

    @Override
    public boolean SemesterOneHasCompleted(String CourseId, String StudentId, Session session) {
        EnrollmentEntity existingEnrollment = session.createQuery("FROM EnrollmentEntity WHERE courseId.courseId = :CourseId AND semesterType = :semesterType AND studentEntity.StudentId = :StudentId AND status=:status", EnrollmentEntity.class)
                .setParameter("CourseId", CourseId)
                .setParameter("semesterType",SemesterTypes.FIRSTSEMESTER)
                .setParameter("status", Status.FINISHED)
                .setParameter("StudentId",StudentId)
                .uniqueResult();

        return existingEnrollment != null;
    }
}
