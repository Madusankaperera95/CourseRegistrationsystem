package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.CourseDto;
import lk.cmjd.coursework.dto.DepartmentDto;
import lk.cmjd.coursework.service.SuperService;

import java.util.ArrayList;

public interface DepartmentService extends SuperService {

    DepartmentDto search(String id) throws Exception;
    ArrayList<DepartmentDto> getAll() throws Exception;
}
