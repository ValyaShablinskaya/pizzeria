package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface IPizzaInfoService {
    void add(PizzaInfo pizzaInfo);
    PizzaInfo findById(Long id);
    List<PizzaInfo> findAll();
    void update(PizzaInfo pizzaInfo, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
}
