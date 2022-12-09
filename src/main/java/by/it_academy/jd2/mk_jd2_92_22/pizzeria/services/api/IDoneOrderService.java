package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.DoneOrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IDoneOrderService extends IEssenceService<DoneOrderDTO> {
    DoneOrderDTO add(DoneOrderDTO doneOrder);
    DoneOrderDTO findById(Long id);
    List<DoneOrderDTO> findAll();
    DoneOrderDTO update(DoneOrderDTO doneOrder, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
