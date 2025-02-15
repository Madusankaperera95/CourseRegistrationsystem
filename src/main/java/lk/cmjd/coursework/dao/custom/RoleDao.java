package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.entity.RoleEntity;

import java.util.ArrayList;

public interface RoleDao extends SuperDao {
    RoleEntity search(String roleName) throws Exception;
    ArrayList<RoleEntity> getAll() throws  Exception;
}
