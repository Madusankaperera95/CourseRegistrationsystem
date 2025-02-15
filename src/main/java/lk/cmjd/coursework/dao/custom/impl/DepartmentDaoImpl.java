package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.DepartmentDao;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;

public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public DepartmentEntity search(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DepartmentEntity department = null;

        try {
            department = session.get(DepartmentEntity.class, id);
            if (department != null) {
                System.out.println("Department retrieved: " + department.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return department;
    }

    @Override
    public ArrayList<DepartmentEntity> getAll() throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ArrayList<DepartmentEntity>) session.createQuery("FROM DepartmentEntity", DepartmentEntity.class).list();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }
}
