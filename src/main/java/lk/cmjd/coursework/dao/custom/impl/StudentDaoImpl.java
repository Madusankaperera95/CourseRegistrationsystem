package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.StudentDao;
import lk.cmjd.coursework.entity.StudentEntity;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public String Save(StudentEntity student) throws Exception {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  // Start Transaction

            session.save(student);  // Save entity

            transaction.commit();  // Commit Transaction
            return "ok";

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // Rollback only if active
            }
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public ArrayList<StudentEntity> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ArrayList<StudentEntity>) session.createQuery("FROM StudentEntity ", StudentEntity.class).list();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }

    @Override
    public String Update(StudentEntity student,Session session) throws Exception {
        try {
            StudentEntity managedStudent = session.merge(student);
            session.update(managedStudent);
            return "ok";
        } catch (Exception e) {
            throw new RuntimeException("Student update failed", e);
        }
    }

    @Override
    public StudentEntity getStudent(String id,Session session) {
        return session.get(StudentEntity.class, id);
    }

    @Override
    public StudentEntity getStudentByEmail(String email, Session session) {
        try {
            return session.createQuery("FROM StudentEntity WHERE Studentemail = :email", StudentEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Error fetching student by email: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<StudentEntity> searchStudentByIdOrEmail(String value, Session session) {
        return  session.createQuery("FROM StudentEntity WHERE StudentId LIKE :studentId OR StudentName LIKE :studentName",StudentEntity.class)
                .setParameter("studentId","%"+value+"%")
                .setParameter("studentName","%"+value+"%")
                .getResultList();
    }

}
