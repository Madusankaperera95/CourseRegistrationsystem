package lk.cmjd.coursework.util;

import java.util.Properties;

import lk.cmjd.coursework.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings (using Environment constants)
                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:mysql://localhost/courseregistrations");
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1234");

                // Auto-generate tables
                settings.put(Environment.HBM2DDL_AUTO, "update"); // "create", "create-drop", or "update"

                configuration.setProperties(settings);

                // Add annotated classes
                configuration.addAnnotatedClass(PreRequisiteEntity.class);
                configuration.addAnnotatedClass(DepartmentEntity.class);
                configuration.addAnnotatedClass(CourseEntity.class);
                configuration.addAnnotatedClass(RoleEntity.class);
                configuration.addAnnotatedClass(UserEntity.class);
                configuration.addAnnotatedClass(StudentEntity.class); // Add other entities
                configuration.addAnnotatedClass(EnrollmentEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                insertDefaultDepartments();
                insertDefaultRoles();
                insertDefaultPreRequisites();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create SessionFactory", e);
            }
        }
        return sessionFactory;
    }

    private static void insertDefaultDepartments() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Check if records already exist
            Long count = (Long) session.createQuery("SELECT COUNT(d) FROM DepartmentEntity d").uniqueResult();
            if (count == 0) {
                session.persist(new DepartmentEntity("Information Technology"));
                session.persist(new DepartmentEntity("Management"));
                session.persist(new DepartmentEntity("Engineering"));
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("comming error:"+e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertDefaultRoles() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Check if records already exist
            Long count = (Long) session.createQuery("SELECT COUNT(r) FROM RoleEntity r").uniqueResult();
            if (count == 0) {
                session.persist(new RoleEntity("Admin"));
                session.persist(new RoleEntity("Student"));
                session.persist(new RoleEntity("Faculty"));
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("comming error:"+e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertDefaultPreRequisites() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Check if records already exist
            Long count = (Long) session.createQuery("SELECT COUNT(pr) FROM PreRequisiteEntity pr").uniqueResult();
            if (count == 0) {
                session.persist(new PreRequisiteEntity("A/L"));
                session.persist(new PreRequisiteEntity("B.sc Degree"));
                session.persist(new PreRequisiteEntity("M.sc degree"));
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("comming error:"+e.getMessage());
            e.printStackTrace();
        }
    }

}
