package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.EnrollmentDao;
import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.entity.CourseEntity;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImpl implements EnrollmentDao {
    @Override
    public String save(EnrollmentEntity enrollmentEntity,Session session) {
        session.save(enrollmentEntity);  // Save entity
        return "ok";
    }

    @Override
    public String update(EnrollmentEntity enrollmentEntity, Session session) {
        session.update(enrollmentEntity);
        return "ok";
    }



    @Override
    public EnrollmentEntity getEnrollment(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        EnrollmentEntity enrollment = null;

        try {
            enrollment = session.get(EnrollmentEntity.class, id);
            if (enrollment != null) {
                System.out.println("Department retrieved: " + enrollment.getEnrollId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return enrollment;
    }

    @Override
    public boolean checkAlreadyRegistered(SemesterTypes semesterType, String CourseId, String StudentId, Session session) {
        EnrollmentEntity existingEnrollment = session.createQuery("FROM EnrollmentEntity WHERE course.courseId = :CourseId AND semesterType = :semesterType AND studentEntity.StudentId = :StudentId", EnrollmentEntity.class)
                .setParameter("CourseId", CourseId)
                .setParameter("semesterType",semesterType)
                .setParameter("StudentId",StudentId)
                .uniqueResult();

        return existingEnrollment != null;
    }

    @Override
    public boolean SemesterOneHasCompleted(String CourseId, String StudentId, Session session) {
        EnrollmentEntity existingEnrollment = session.createQuery("FROM EnrollmentEntity WHERE course.courseId = :CourseId AND semesterType = :semesterType AND studentEntity.StudentId = :StudentId AND status=:status", EnrollmentEntity.class)
                .setParameter("CourseId", CourseId)
                .setParameter("semesterType",SemesterTypes.FIRSTSEMESTER)
                .setParameter("status", Status.COMPLETED)
                .setParameter("StudentId",StudentId)
                .uniqueResult();

        return existingEnrollment != null;
    }

    @Override
    public boolean semesterHasCompleted(int id, Session session) {
        EnrollmentEntity existingEnrollment = session.createQuery("FROM EnrollmentEntity where enrollId = :enrollId AND status = :status",EnrollmentEntity.class)
                .setParameter("enrollId",id)
                .setParameter("status",Status.COMPLETED)
                .uniqueResult();
        return existingEnrollment != null;

    }

    @Override
    public String delete(int id, Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();  // Start transaction
            EnrollmentEntity enrollment = session.get(EnrollmentEntity.class, id);
            System.out.println(enrollment);
            if (enrollment != null) {
                System.out.println("calling");
                session.delete(enrollment);
            }
            transaction.commit();  // Commit the transaction
            return "ok";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback if an error occurs
            }
            e.printStackTrace();
            return "error";
        }
    }


    @Override
    public ArrayList<EnrollmentEntity> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ArrayList<EnrollmentEntity>) session.createQuery("FROM EnrollmentEntity", EnrollmentEntity.class).list();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }

    @Override
    public ArrayList<EnrollmentEntity> getEnrollmentsByStudentId(String studentId,Session session) {
        ArrayList<EnrollmentEntity> enrollments = (ArrayList<EnrollmentEntity>) session.createQuery("FROM EnrollmentEntity WHERE studentEntity.StudentId = :StudentId", EnrollmentEntity.class)
                .setParameter("StudentId", studentId)
                .list();

        return enrollments;
    }

    @Override
    public ArrayList<EnrollmentEntity> getEnrollmentsForFilter(String courseId, SemesterTypes semesterType, Session session) {
        ArrayList<EnrollmentEntity> enrollments = (ArrayList<EnrollmentEntity>) session.createQuery("FROM EnrollmentEntity WHERE course.courseId = :CourseId AND semesterType = :semester", EnrollmentEntity.class)
                .setParameter("CourseId", courseId)
                .setParameter("semester", semesterType)
                .list();

        return enrollments;
    }

    @Override
    public int getEnrolledStudentsPerCourse(String courseID, SemesterTypes semesterType, Session session) {
        Long enrolledStudents = session.createQuery(
                        "SELECT COUNT(e) FROM EnrollmentEntity e WHERE e.course.courseId = :courseId AND e.semesterType = :semesterType",
                        Long.class)
                .setParameter("courseId", courseID)
                .setParameter("semesterType", semesterType)
                .uniqueResult();

        return enrolledStudents != null ? enrolledStudents.intValue() : 0;
    }

    @Override
    public List<Object[]> getEnrollmentCount(Session session) {
        return session.createQuery(
                "SELECT c.courseName, COUNT(e.course.courseId) FROM CourseEntity c " +
                        "LEFT JOIN EnrollmentEntity e ON e.course.courseId = c.courseId " +
                        "WHERE e.semesterType = :semesterType "+
                        "GROUP BY c.courseName",
                Object[].class
        ).setParameter("semesterType",SemesterTypes.FIRSTSEMESTER).getResultList();
    }
}
