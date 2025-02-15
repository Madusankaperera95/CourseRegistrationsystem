package lk.cmjd.coursework.service.custom.Impl;

import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.DepartmentDao;
import lk.cmjd.coursework.dao.custom.UserDao;
import lk.cmjd.coursework.dto.UserDto;
import lk.cmjd.coursework.service.custom.LoginService;

public class LoginServiceImpl implements LoginService {
    private UserDao userdao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);

    @Override
    public String Login(UserDto userDto) {
         return userdao.Login(userDto.getEmail(),userDto.getPassword());
    }
}
