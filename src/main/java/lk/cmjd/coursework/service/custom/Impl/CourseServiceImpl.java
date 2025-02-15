package lk.cmjd.coursework.service.custom.Impl;

import java.util.ArrayList;

import lk.cmjd.coursework.dao.custom.CourseDao;
import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.DepartmentDao;
import lk.cmjd.coursework.dao.custom.PrerequisiteDao;
import lk.cmjd.coursework.dto.CourseDto;
import lk.cmjd.coursework.entity.CourseEntity;
import lk.cmjd.coursework.service.custom.CourseService;
import lk.cmjd.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = (CourseDao)  DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.COURSE);
    private DepartmentDao departmentDao = (DepartmentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.DEPARTMENT);

    private PrerequisiteDao prerequisiteDao = (PrerequisiteDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.PREREQUISITE);

    @Override
    public String save(CourseDto courseDto) throws Exception {
        var departmentEntity = departmentDao.search(Integer.toString(courseDto.getDepartment()));
        var preRequisiteEntity = prerequisiteDao.getPreRequestById(Integer.toString(courseDto.getPrerequisites()));
        CourseEntity courseEntity = new CourseEntity(
                courseDto.getCourseId(),
                courseDto.getCourseName(),
                courseDto.getCreditHours(),
                departmentEntity,
                courseDto.getMaximumCapacity(),
                preRequisiteEntity,
                courseDto.getDescription());
        return courseDao.Save(courseEntity);
    }

    @Override
    public String update(CourseDto courseDto) throws Exception {
        var DepartmentEntity = departmentDao.search(Integer.toString(courseDto.getDepartment()));
        var preRequisiteEntity = prerequisiteDao.getPreRequestById(Integer.toString(courseDto.getPrerequisites()));
        CourseEntity courseEntity = new CourseEntity(
                courseDto.getCourseId(),
                courseDto.getCourseName(),
                courseDto.getCreditHours(),
                DepartmentEntity,
                courseDto.getMaximumCapacity(),
                preRequisiteEntity,
                courseDto.getDescription());
        return courseDao.Update(courseEntity);
    }

    @Override
    public String delete(String id) throws Exception {
         return courseDao.delete(id);
    }

    @Override
    public CourseDto search(String id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public ArrayList<CourseDto> getAll() throws Exception {
        ArrayList<CourseDto> courses = new ArrayList<>();
        var courseEntities = courseDao.getAll();
        for(CourseEntity courseEntity : courseEntities){
            courses.add(new CourseDto(courseEntity.getCourseId(), courseEntity.getCourseName(), courseEntity.getCreditHours(), courseEntity.getDepartment().getName(),courseEntity.getPreRequisite().getRequisiteName(),courseEntity.getMaximumCapacity(),courseEntity.getDescription()));
        }
       return courses;
    }

   
    
}
