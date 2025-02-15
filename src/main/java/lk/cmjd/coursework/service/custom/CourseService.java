package lk.cmjd.coursework.service.custom;

import java.util.ArrayList;

import lk.cmjd.coursework.dto.CourseDto;
import lk.cmjd.coursework.service.SuperService;



public interface CourseService extends SuperService {
    String save(CourseDto courseDto) throws Exception;
    String update(CourseDto courseDto) throws Exception;
    String delete(String id) throws Exception;
    CourseDto search(String id) throws Exception;
    ArrayList<CourseDto> getAll() throws Exception;
}
