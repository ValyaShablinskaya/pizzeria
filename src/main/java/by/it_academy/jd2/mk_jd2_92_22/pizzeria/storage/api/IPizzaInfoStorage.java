package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.PizzaInfo;

import java.util.List;
import java.util.Optional;

public interface IPizzaInfoStorage {
    List<PizzaInfo> findAll();
    Optional<PizzaInfo> add(PizzaInfo pizzaInfo);
    Optional<PizzaInfo> findByName(String name);
    void delete(PizzaInfo pizzaInfo);
}