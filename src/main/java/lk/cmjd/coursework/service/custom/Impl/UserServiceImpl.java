package lk.cmjd.coursework.service.custom.Impl;

import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.StudentDao;
import lk.cmjd.coursework.dao.custom.UserDao;
import lk.cmjd.coursework.dto.ChangePasswordDto;
import lk.cmjd.coursework.entity.StudentEntity;
import lk.cmjd.coursework.entity.UserEntity;
import lk.cmjd.coursework.service.custom.UserService;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    private UserDao userdao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);

    private StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.STUDENT);

    @Override
    public String changePassword(ChangePasswordDto changePasswordDto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        String message = "";

        try {
            transaction = session.beginTransaction(); // Start Transaction

            if (!userdao.CheckOldPasswordCorrect(changePasswordDto.getEmail(), changePasswordDto.getOldPassword(), session)) {
                throw new Exception("Password you entered is wrong");
            }

            if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
                throw new Exception("New password and confirm password don't match");
            }

            StudentEntity studentEntity = studentDao.getStudentByEmail(changePasswordDto.getEmail(),session);

            message = userdao.Update(new UserEntity(studentEntity.getUser().getUserId(),studentEntity.getUser().getUserName(),changePasswordDto.getEmail(),studentEntity.getUser().getRole() ,changePasswordDto.getNewPassword()), session);

            transaction.commit(); // Commit changes

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback on failure
            }
            message = e.getMessage();
            System.out.println(message);

        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Close session to prevent leaks
            }
        }

        return message;

    }
}
