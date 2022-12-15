package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPizzaDao extends JpaRepository<Pizza, Long> {
    //List<Pizza> findAllPizzasByIdDoneOrder(Long id);
}
