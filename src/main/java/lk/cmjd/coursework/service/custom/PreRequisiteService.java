package lk.cmjd.coursework.service.custom;

import lk.cmjd.coursework.dto.PreRequisiteDto;
import lk.cmjd.coursework.service.SuperService;

import java.util.ArrayList;

public interface PreRequisiteService extends SuperService {

     PreRequisiteDto search(String name) throws Exception;
     ArrayList<PreRequisiteDto> getAll() throws Exception;
}
