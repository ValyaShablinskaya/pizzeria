package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IStageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderStatusMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IStageMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IStageService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.StageDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StageService implements IStageService {
    private final IStageDao stageDao;
    private final IOrderStatusDao orderStatusDao;
    private final IStageMapper stageMapper;
    private final IOrderStatusMapper orderStatusMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Stage is not found";

    public StageService(IStageDao stageDao, IOrderStatusDao orderStatusDao, IStageMapper stageMapper,
                        IOrderStatusMapper orderStatusMapper) {
        this.stageDao = stageDao;
        this.orderStatusDao = orderStatusDao;
        this.stageMapper = stageMapper;
        this.orderStatusMapper = orderStatusMapper;
    }

    @Override
    @Transactional
    public StageDTO create(StageDTO stageDTO) {
        Stage stage = stageMapper.convertToStage(stageDTO);
        stage.setCreationDate(LocalDateTime.now());
        stage.setUpdateDate(stage.getCreationDate());
        stage = stageDao.save(stage);
        return stageMapper.convertToStageDTO(stage);
    }

    @Override
    public StageDTO read(Long id) {
        Stage stage = stageDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return stageMapper.convertToStageDTO(stage);
    }

    @Override
    public List<StageDTO> get() {
        List<Stage> stages = stageDao.findAll();
        return stageMapper.convertToStageList(stages);
    }

    @Override
    @Transactional
    public StageDTO update(StageDTO stageDTO, Long id, LocalDateTime updateDate) {
        Stage stageToUpdate = stageDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!stageToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Stage has been already edited");
        }
        stageToUpdate.setUpdateDate(LocalDateTime.now());
        stageToUpdate.setDescription(stageDTO.getDescription());
        OrderStatus orderStatus = orderStatusMapper.convertToOrderStatus(stageDTO.getOrderStatus());
        stageToUpdate.setOrderStatus(orderStatus);
        stageToUpdate = stageDao.save(stageToUpdate);
        return stageMapper.convertToStageDTO(stageToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id, LocalDateTime updateDate) {
        Stage stageToDelete = stageDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!stageToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Stage has been already edited");
        }
        stageDao.deleteById(id);
    }
}