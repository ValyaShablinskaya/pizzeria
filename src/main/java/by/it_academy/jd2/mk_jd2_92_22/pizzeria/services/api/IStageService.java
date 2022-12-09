package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.StageDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IStageService extends IEssenceService<StageDTO>{
    StageDTO add(StageDTO stage);
    StageDTO findById(Long id);
    List<StageDTO> findAll();
    StageDTO update(StageDTO stage, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
    void  addStageOnOrderStatus(Long stageId, Long orderStatusId);
    List<StageDTO> findAllByIdOrderStatus(Long id);
}
