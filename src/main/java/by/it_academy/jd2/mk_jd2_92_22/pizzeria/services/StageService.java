package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.StageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IStageMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IStageService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.StageDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class StageService implements IStageService {
    private final StageDao stageDao;
    private final OrderStatusDao orderStatusDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Stage is not found";

    public StageService(StageDao stageDao, OrderStatusDao orderStatusDao) {
        this.stageDao = stageDao;
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public StageDTO add(StageDTO stageDTO) {
        Stage stage = IStageMapper.INSTANCE.convertToStage(stageDTO);
        stage.setCreationDate(LocalDateTime.now());
        stage.setUpdateDate(stage.getCreationDate());
        stage = stageDao.save(stage).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IStageMapper.INSTANCE.convertToStageDTO(stage);
    }

    @Override
    public StageDTO findById(Long id) {
        Stage stage = stageDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IStageMapper.INSTANCE.convertToStageDTO(stage);
    }

    @Override
    public List<StageDTO> findAll() {
        List<Stage> stages = stageDao.findAll();
        return IStageMapper.INSTANCE.convertToStageList(stages);
    }

    @Override
    public StageDTO update(StageDTO stageDTO, Long id, LocalDateTime updateDate) {
        Stage stageToUpdate = stageDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!stageToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Stage has been already edited");
        }
        stageToUpdate.setUpdateDate(LocalDateTime.now());
        stageToUpdate.setDescription(stageDTO.getDescription());
        stageToUpdate = stageDao.update(stageToUpdate).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IStageMapper.INSTANCE.convertToStageDTO(stageToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Stage stageToDelete = stageDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!stageToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Stage has been already edited");
        }
        stageDao.deleteById(id);
    }

    @Override
    public void addStageOnOrderStatus(Long stageId, Long orderStatusId) {
        if (!stageDao.findById(stageId).isPresent()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION);
        }
        if (!orderStatusDao.findById(orderStatusId).isPresent()) {
            throw new EntityNotFoundException("Order status is not found");
        }
        stageDao.addStageOnOrderStatus(stageId, orderStatusId);
    }

    @Override
    public List<StageDTO> findAllByIdOrderStatus(Long id) {
        List<Stage> stages = stageDao.findAllStageByIdOrderStatus(id);
        return IStageMapper.INSTANCE.convertToStageList(stages);
    }
}