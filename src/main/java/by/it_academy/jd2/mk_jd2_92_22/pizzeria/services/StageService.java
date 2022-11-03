package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.StageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IStageService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class StageService implements IStageService {
    private final StageDao stageDao;

    public StageService(StageDao stageDao) {
        this.stageDao = stageDao;
    }

    @Override
    public void add(Stage stage) {
        stage.setCreationDate(LocalDateTime.now());
        stageDao.save(stage);
    }

    @Override
    public Stage findById(Long id) {
        return stageDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Stage is not found"));
    }

    @Override
    public List<Stage> findAll() {
        return stageDao.findAll();
    }

    @Override
    public void update(Stage stage, Long id, LocalDateTime updateDate) {
        Stage stageToUpdate = stageDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Stage is not found"));
        if (!stageToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Stage has been already edited");
        }
        stageToUpdate.setUpdateDate(LocalDateTime.now());
        stageToUpdate.setDescription(stage.getDescription());

        stageDao.update(stageToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Stage stageToDelete = stageDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Stage is not found"));
        if (!stageToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Stage has been already edited");
        }
        stageDao.deleteById(id);
    }
}