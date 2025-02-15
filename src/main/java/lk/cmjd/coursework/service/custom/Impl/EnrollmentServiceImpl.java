package lk.cmjd.coursework.service.custom.Impl;

import lk.cmjd.coursework.dao.custom.CourseDao;
import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.EnrollmentDao;
import lk.cmjd.coursework.dao.custom.StudentDao;
import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.entity.CourseEntity;
import lk.cmjd.coursework.entity.EnrollmentEntity;
import lk.cmjd.coursework.entity.StudentEntity;
import lk.cmjd.coursework.service.custom.EnrollmentService;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnrollmentServiceImpl implements EnrollmentService {
    private CourseDao courseDao = (CourseDao)  DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.COURSE);

    private StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.STUDENT);

    private EnrollmentDao enrollmentDao = (EnrollmentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ENROLLMENT);

    @Override
    public String save(EnrollmentDto enrollmentDto) throws Exception {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            CourseEntity course = courseDao.getCourse(enrollmentDto.getCourseId(),session);
            if(course == null){
                throw new Exception("course Not Found");
            }

            // Fetch the student using the current session
            StudentEntity studentEntity = studentDao.getStudent(enrollmentDto.getStudentId(), session);
            if (studentEntity == null) {
                throw new Exception("Student not found");
            }

            if(enrollmentDao.checkAlreadyRegistered(enrollmentDto.getSemester(),enrollmentDto.getCourseId(),enrollmentDto.getStudentId(),session)){
                throw new Exception("Already registered for the Semester");
            }

            if(!enrollmentDao.SemesterOneHasCompleted(enrollmentDto.getCourseId(),enrollmentDto.getStudentId(),session) && enrollmentDto.getSemester().equals(SemesterTypes.SECONDSEMESTER)){
                throw new Exception("Semester one is  not completed yet Completed");
            }
            enrollmentDao.save(new EnrollmentEntity(course,enrollmentDto.getSemester(),enrollmentDto.getStatus(),studentEntity),session);


            transaction.commit();
            return "ok";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return e.getMessage(); // Or return "error" based on your error handling
        } finally {
            session.close();
        }
    }
}
