package lk.cmjd.coursework.service.custom.Impl;

import lk.cmjd.coursework.dao.custom.DaoFactory;
import lk.cmjd.coursework.dao.custom.PrerequisiteDao;
import lk.cmjd.coursework.dto.PreRequisiteDto;
import lk.cmjd.coursework.entity.PreRequisiteEntity;
import lk.cmjd.coursework.service.custom.PreRequisiteService;

import java.util.ArrayList;

public class PreRequisiteServiceImpl implements PreRequisiteService {

    private PrerequisiteDao prerequisiteDao = (PrerequisiteDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.PREREQUISITE);

    @Override
    public PreRequisiteDto search(String name) throws Exception {
        var preRequisiteDto = new PreRequisiteDto();
        var preRequisiteEntity = prerequisiteDao.search(name);
        preRequisiteDto.setPreRequisiteId(preRequisiteEntity.getPreRequisiteId());
        preRequisiteDto.setRequisiteName(preRequisiteEntity.getRequisiteName());
        return preRequisiteDto;
    }

    @Override
    public ArrayList<PreRequisiteDto> getAll() throws Exception {
        var preRequisiteEntities = prerequisiteDao.getAll();
        ArrayList<PreRequisiteDto> preRequisites = new ArrayList<>();

        for(PreRequisiteEntity preRequisiteEntity : preRequisiteEntities)
        {
            preRequisites.add(new PreRequisiteDto(preRequisiteEntity.getPreRequisiteId(),preRequisiteEntity.getRequisiteName()));
        }

        return preRequisites;

    }
}
