package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.RoleDao;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.entity.RoleEntity;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class RoleDaoImpl implements RoleDao {
    @Override
    public RoleEntity search(String roleName) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleEntity role = null;
        String hql = "FROM RoleEntity Where roleName = :roleName";
        Query<RoleEntity> query = session.createQuery(hql, RoleEntity.class);
        query.setParameter("roleName", roleName);

        try {
            role = query.uniqueResult();
            if (role != null) {
                System.out.println("Role retrieved: " + role.getRoleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return role;
    }

    @Override
    public ArrayList<RoleEntity> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ArrayList<RoleEntity>) session.createQuery("FROM RoleEntity", RoleEntity.class).list();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }
}
