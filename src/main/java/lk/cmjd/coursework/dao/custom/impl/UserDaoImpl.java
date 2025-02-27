package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.UserDao;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.entity.UserEntity;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {
    @Override
    public int Save(UserEntity userEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();  // Start Transaction
            int id=(Integer) session.save(userEntity);
            transaction.commit();  // Commit Transaction
            return id;

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();  // Rollback only if active
            }
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public UserEntity Search(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserEntity user = null;

        try {
            user = session.get(UserEntity.class, id);
            if (user != null) {
                System.out.println("user retrieved: " + user.getUserName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public String Login(String email, String Password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserEntity user = null;

        try {
            Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE userEmail = :email", UserEntity.class);
            query.setParameter("email", email);
            user = query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return (user != null && user.verifyPassword(Password) && user.getRole().getRoleName().equals("Admin")) ? "Admin" : (user != null && user.verifyPassword(Password) && user.getRole().getRoleName().equals("Faculty") ? "Faculty" : (user != null && user.verifyPassword(Password) && user.getRole().getRoleName().equals("Student")) ? "Student" : "error");
    }

    @Override
    public String Update(UserEntity userEntity,Session session) {
        try {
            System.out.println(userEntity.toString());
            UserEntity managedUser = session.merge(userEntity);
            session.update(managedUser); // Or merge if necessary
            return "ok";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("User update failed" + e.getMessage());
        }
    }

    @Override
    public boolean CheckOldPasswordCorrect(String email, String OldPassword,Session session) {

        UserEntity user = null;

        try {
            Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE userEmail = :email", UserEntity.class);
            query.setParameter("email", email);
            user = query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user.verifyPassword(OldPassword);
    }
}
