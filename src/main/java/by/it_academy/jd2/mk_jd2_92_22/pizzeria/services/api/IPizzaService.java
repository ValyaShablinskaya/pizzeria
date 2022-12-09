package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IPizzaService extends IEssenceService<PizzaDTO>{
    PizzaDTO add(PizzaDTO pizza);
    PizzaDTO findById(Long id);
    List<PizzaDTO> findAll();
    PizzaDTO update(PizzaDTO pizza, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
    List<PizzaDTO> findAllByIdDoneOrder(Long id);
}
