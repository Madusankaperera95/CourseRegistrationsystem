package lk.cmjd.coursework.dao.custom.impl;

import lk.cmjd.coursework.dao.custom.PrerequisiteDao;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.entity.PreRequisiteEntity;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PreRequisiteDaoImpl implements PrerequisiteDao {
    @Override
    public PreRequisiteEntity search(String name) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PreRequisiteEntity preRequisite = null;
        String hql = "FROM PreRequisiteEntity Where requisiteName = :name";
        Query<PreRequisiteEntity> query = session.createQuery(hql, PreRequisiteEntity.class);
        query.setParameter("requisiteName", name);

        try {
            preRequisite = query.uniqueResult();
            if (preRequisite != null) {
                System.out.println("PreRequisite retrieved: " + preRequisite.getRequisiteName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return preRequisite;
    }

    @Override
    public ArrayList<PreRequisiteEntity> getAll() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (ArrayList<PreRequisiteEntity>) session.createQuery("FROM PreRequisiteEntity", PreRequisiteEntity.class).list();

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());


            return null;
        }
    }

    @Override
    public PreRequisiteEntity getPreRequestById(String id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PreRequisiteEntity preRequisite = null;

        try {
            preRequisite = session.get(PreRequisiteEntity.class, id);
            if (preRequisite != null) {
                System.out.println("PreRequisiteEntity retrieved: " + preRequisite.getRequisiteName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return preRequisite;
    }
}
