package lk.cmjd.coursework.service.custom.Impl;

import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.DepartmentDao;
import lk.cmjd.coursework.dto.DepartmentDto;
import lk.cmjd.coursework.entity.DepartmentEntity;
import lk.cmjd.coursework.service.custom.DepartmentService;

import java.util.ArrayList;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao = (DepartmentDao)  DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.DEPARTMENT);

    @Override
    public DepartmentDto search(String id) throws Exception {
        var departmentDto = new DepartmentDto();
        var departmentEntity = departmentDao.search(id);
        departmentDto.setId(departmentEntity.getId());
        departmentDto.setName(departmentEntity.getName());

        return departmentDto;

    }

    @Override
    public ArrayList<DepartmentDto> getAll() throws Exception {
        var departments = departmentDao.getAll();
        ArrayList<DepartmentDto> departmentlist= new ArrayList<>();

        for(DepartmentEntity departmentEntity : departments){
            departmentlist.add(new DepartmentDto(departmentEntity.getId(),departmentEntity.getName()));
        }

        return departmentlist;

    }
}
