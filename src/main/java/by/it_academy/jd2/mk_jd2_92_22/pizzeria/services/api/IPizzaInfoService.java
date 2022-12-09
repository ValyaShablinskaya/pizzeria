package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IPizzaInfoService extends IEssenceService<PizzaInfoDTO>{
    PizzaInfoDTO add(PizzaInfoDTO pizzaInfo);
    PizzaInfoDTO findById(Long id);
    List<PizzaInfoDTO> findAll();
    PizzaInfoDTO update(PizzaInfoDTO pizzaInfo, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
}
