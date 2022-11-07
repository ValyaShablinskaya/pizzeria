package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;

import java.util.List;

public interface IStageDao extends ICrudDao<Stage, Long> {
    void  addStageOnOrderStatus(Long stageId, Long orderStatusId);
    List<Stage> findAllStageByIdOrderStatus(Long id);
}
