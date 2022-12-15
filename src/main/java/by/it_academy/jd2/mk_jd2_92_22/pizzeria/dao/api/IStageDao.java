package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStageDao extends JpaRepository<Stage, Long> {
    //void  addStageOnOrderStatus(Long stageId, Long orderStatusId);
    //List<Stage> findAllStageByIdOrderStatus(Long id);
}
