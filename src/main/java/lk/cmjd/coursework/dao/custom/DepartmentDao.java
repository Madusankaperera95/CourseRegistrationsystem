package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.dto.CourseDto;
import lk.cmjd.coursework.entity.DepartmentEntity;

import java.util.ArrayList;

public interface DepartmentDao extends SuperDao {
    DepartmentEntity search(String id) throws Exception;
    ArrayList<DepartmentEntity> getAll() throws Exception;
}
