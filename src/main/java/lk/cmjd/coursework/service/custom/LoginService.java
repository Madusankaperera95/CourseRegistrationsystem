package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.UserDto;
import lk.cmjd.coursework.service.SuperService;

public interface LoginService extends SuperService {
    String Login(UserDto userDto);
}
