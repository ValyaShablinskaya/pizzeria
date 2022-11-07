package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;

import java.util.List;

public interface IPizzaDao extends ICrudDao<Pizza, Long>{
    List<Pizza> findAllPizzasByIdDoneOrder(Long id);
}
