package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;

import java.time.LocalDateTime;
import java.util.List;

public interface IPizzaService {
    void add(Pizza pizza);
    Pizza findById(Long id);
    List<Pizza> findAll();
    void update(Pizza pizza, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
    List<Pizza> findAllByIdDoneOrder(Long id);
}
