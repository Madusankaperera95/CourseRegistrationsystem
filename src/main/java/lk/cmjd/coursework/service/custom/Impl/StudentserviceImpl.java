package lk.cmjd.coursework.service.custom.Impl;

import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.RoleDao;
import lk.cmjd.coursework.dao.custom.StudentDao;
import lk.cmjd.coursework.dao.custom.UserDao;
import lk.cmjd.coursework.dto.StudentDto;
import lk.cmjd.coursework.entity.StudentEntity;
import lk.cmjd.coursework.entity.UserEntity;
import lk.cmjd.coursework.service.custom.StudentService;
import lk.cmjd.coursework.util.EmailSender;
import lk.cmjd.coursework.util.HibernateUtil;
import lk.cmjd.coursework.util.PasswordGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentserviceImpl implements StudentService {
    private UserDao userDao = (UserDao)  DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);
    private RoleDao roleDao = (RoleDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ROLE);

    private StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.STUDENT);

    @Override
    public String save(StudentDto studentDto) throws Exception {
        System.out.println(studentDto.toString());
        studentDto.setPassword(PasswordGenerator.generatePassword());
        String role = "Student";
        var roleEntity = roleDao.search(role);
        System.out.println(roleEntity.getId());
        int userId = userDao.Save(new UserEntity(studentDto.getStudentName(),studentDto.getEmail(),studentDto.getPassword(),roleEntity));
        var User = userDao.Search(String.valueOf(userId));
        System.out.println(userId);

        String output =studentDao.Save(new StudentEntity(studentDto.getStudentId(),studentDto.getStudentName(),studentDto.getEmail(),studentDto.getDob(),studentDto.getProgram(),studentDto.getYear(),studentDto.getContactNumber(),User));
        if(output == "ok"){
            EmailSender.sendEmail(studentDto.getEmail(),"Crm Password","Your Password is :"+studentDto.getPassword());
        }

        return output;
    }

    @Override
    public ArrayList<StudentDto> getAll() throws Exception {
        ArrayList<StudentEntity> studentEntities = studentDao.getAll();
        return getStudentDtos(studentEntities);
    }

    @Override
    public String update(StudentDto studentDto) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Fetch the student using the current session
            StudentEntity studentEntity = studentDao.getStudent(studentDto.getStudentId(), session);
            if (studentEntity == null) {
                throw new Exception("Student not found");
            }


            userDao.Update(new UserEntity(studentEntity.getUser().getUserId(),studentDto.getStudentName(),studentDto.getEmail(),studentEntity.getUser().getPassword(),studentEntity.getUser().getRole()), session); // Assume update uses session.merge() or similar

            // Update StudentEntity (modify the managed entity)

            studentDao.Update(new StudentEntity(studentDto.getStudentId(),studentDto.getStudentName(),studentDto.getEmail(),studentDto.getDob(),studentDto.getProgram(),studentDto.getYear(),studentDto.getContactNumber(),studentEntity.getUser()), session);

            transaction.commit();
            return "ok";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e; // Or return "error" based on your error handling
        } finally {
            session.close();
        }
    }

    @Override
    public StudentDto getStudent(String email) {
        System.out.println(email);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Fetch the student using the current session
            StudentEntity studentEntity = studentDao.getStudentByEmail(email, session);
            if (studentEntity == null) {
                throw new Exception("Student not found");
            }

            System.out.println("successfully adding");
            return new StudentDto(studentEntity.getStudentId(),studentEntity.getStudentName(),studentEntity.getProgram(),studentEntity.getStudentemail(),studentEntity.getStudentDob(),studentEntity.getYear(),studentEntity.getContactNumber());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error in student retreival");
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<StudentDto> searchStudentByIdOrEmail(String value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Fetch the student using the current session
            List<StudentEntity> studentEntities = studentDao.searchStudentByIdOrEmail(value,session);
            return getStudentDtos(studentEntities);


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error in student retreival");
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    private ArrayList<StudentDto> getStudentDtos(List<StudentEntity> studentEntities) {
        ArrayList<StudentDto> students = new ArrayList<>();
        for(StudentEntity studentEntity : studentEntities){
            students.add(new StudentDto(studentEntity.getStudentId(),studentEntity.getStudentName(),studentEntity.getProgram(),studentEntity.getStudentemail(),studentEntity.getStudentDob(),studentEntity.getYear(),studentEntity.getContactNumber()));
        }

        return students;
    }


}
