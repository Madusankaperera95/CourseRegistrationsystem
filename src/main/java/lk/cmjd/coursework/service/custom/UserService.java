package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.ChangePasswordDto;
import lk.cmjd.coursework.service.SuperService;

public interface UserService extends SuperService {

    String changePassword(ChangePasswordDto changePasswordDto);
}
