package lk.cmjd.coursework.dao.custom;

import lk.cmjd.coursework.dao.SuperDao;
import lk.cmjd.coursework.entity.PreRequisiteEntity;

import java.util.ArrayList;

public interface PrerequisiteDao extends SuperDao {

    PreRequisiteEntity search(String name) throws Exception;
    ArrayList<PreRequisiteEntity> getAll() throws Exception;
    PreRequisiteEntity getPreRequestById(String id) throws  Exception;
}
