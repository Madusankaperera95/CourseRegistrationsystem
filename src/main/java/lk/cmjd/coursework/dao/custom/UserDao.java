package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.entity.UserEntity;
import org.hibernate.Session;

public interface UserDao extends SuperDao {

    int Save(UserEntity userEntity);
    UserEntity Search(String id);

    String Login(String email,String Password);

    String Update(UserEntity userEntity, Session session);


}
