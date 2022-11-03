package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;

import java.time.LocalDateTime;
import java.util.List;

public interface IStageService {
    void add(Stage stage);
    Stage findById(Long id);
    List<Stage> findAll();
    void update(Stage stage, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
