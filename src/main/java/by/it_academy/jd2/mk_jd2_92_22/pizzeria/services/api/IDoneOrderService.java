package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface IDoneOrderService {
    void add(DoneOrder doneOrder);
    DoneOrder findById(Long id);
    List<DoneOrder> findAll();
    void update(DoneOrder doneOrder, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
