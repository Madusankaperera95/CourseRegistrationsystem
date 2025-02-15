package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.CourseDao;
import lk.cmjd.coursework.entity.CourseEntity;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {
    @Override
    public String Save(CourseEntity courseEntity) throws Exception {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  // Start Transaction

            session.save(courseEntity);  // Save entity

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
    public ArrayList<CourseEntity> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ArrayList<CourseEntity>) session.createQuery("FROM CourseEntity", CourseEntity.class).list();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }

    @Override
    public CourseEntity getCourse(String id,Session session) {
        return session.get(CourseEntity.class, id);
    }

    @Override
    public String delete(String id) {
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();  // Start Transaction

            CourseEntity course = session.get(CourseEntity.class, id);
            if (course != null) {
                session.delete(course);
                transaction.commit();
                System.out.println("Course deleted successfully");
            }

            return "ok";

       }
        catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }

    @Override
    public String Update(CourseEntity courseEntity) throws Exception {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  // Start Transaction

            session.update(courseEntity);  // Save entity

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
}
