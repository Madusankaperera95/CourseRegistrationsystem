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
import lk.cmjd.coursework.util.Enums.Status;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

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

    @Override
    public String update(EnrollmentDto enrollmentDto) throws Exception {
        EnrollmentEntity enrollmentEntity = enrollmentDao.getEnrollment(Integer.parseInt(enrollmentDto.getEnrollId()));
        if(enrollmentEntity == null){
            throw new Exception("Enrollment Not Found");
        }

        if(enrollmentDto.getGpa() > 4.0 && enrollmentDto.getGpa() < 0)
        {
            throw new Exception("Invalid GPA");
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            enrollmentDao.update(new EnrollmentEntity(enrollmentEntity.getEnrollId(),enrollmentEntity.getCourse(),enrollmentEntity.getSemesterType(), Status.COMPLETED,enrollmentEntity.getStudentEntity(),enrollmentDto.getGpa(),getGrade(enrollmentDto.getGpa())),session);


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


    private static String getGrade(double gpa) {
        int roundedGpa = (int) Math.round(gpa); // Rounding GPA for switch case

        switch (roundedGpa) {
            case 4:
                return "A";
            case 3:
                return "B";
            case 2:
                return "C";
            case 1:
                return "D";
            case 0:
                return "F";
            default:
                return "Invalid GPA";
        }
    }

    @Override
    public ArrayList<EnrollmentDto> getAll() throws Exception {
        ArrayList<EnrollmentDto> enrollments = new ArrayList<>();
        ArrayList<EnrollmentEntity> enrollmentEntities = enrollmentDao.getAll();

        for(EnrollmentEntity enrollmentEntity : enrollmentEntities)
        {
            enrollments.add(new EnrollmentDto(String.valueOf(enrollmentEntity.getEnrollId()),enrollmentEntity.getCourse().getCourseId(),enrollmentEntity.getSemesterType(),enrollmentEntity.getStatus(),enrollmentEntity.getStudentEntity().getStudentId(),enrollmentEntity.getGpa(),enrollmentEntity.getGrade(),enrollmentEntity.getStudentEntity().getStudentName(),enrollmentEntity.getCourse().getCourseName()));
        }

        return enrollments;
    }

    @Override
    public ArrayList<EnrollmentDto> getEnrollmentsByStudentId(String StudentId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        var student = studentDao.getStudent(StudentId,session);

        if(student == null){
            throw new Exception("Student Not Found");
        }

        ArrayList<EnrollmentDto> enrollemnts = new ArrayList<>();

        var enrollmentEntities = enrollmentDao.getEnrollmentsByStudentId(StudentId,session);

        for(EnrollmentEntity enrollmentEntity : enrollmentEntities)
        {
            enrollemnts.add(new EnrollmentDto(String.valueOf(enrollmentEntity.getEnrollId()),enrollmentEntity.getCourse().getCourseId(),enrollmentEntity.getSemesterType(),enrollmentEntity.getStatus(),enrollmentEntity.getStudentEntity().getStudentId(),enrollmentEntity.getGpa(),enrollmentEntity.getGrade(),enrollmentEntity.getStudentEntity().getStudentName(),enrollmentEntity.getCourse().getCourseName()));
        }

        return enrollemnts;

    }

    @Override
    public String deleteEnrollment(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String resp = enrollmentDao.delete(id,session);
        return resp;
    }


}
