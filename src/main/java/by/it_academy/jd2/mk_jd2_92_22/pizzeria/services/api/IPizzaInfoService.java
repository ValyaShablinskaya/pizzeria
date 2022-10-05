package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.PizzaInfo;

import java.util.List;
import java.util.Optional;

public interface IPizzaInfoService {
    List<PizzaInfo> findAll();
    PizzaInfo add(PizzaInfo pizzaInfo);
    PizzaInfo findByName(String name);
    void delete(PizzaInfo pizzaInfo);
    void validate(PizzaInfo pizzaInfo);
}
