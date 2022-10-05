package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.PizzaInfo;

public interface IMenuRowService {
    void add(MenuRow menuRow);
    void delete(MenuRow menuRow);
    void validate(MenuRow menuRow);
}
