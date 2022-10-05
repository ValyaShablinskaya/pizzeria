package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.MenuRow;

import java.util.List;
import java.util.Optional;

public interface IMenuStorage {
    List<MenuRow> findAll();
    void add(MenuRow menuRow);
    void delete(MenuRow menuRow);
}
