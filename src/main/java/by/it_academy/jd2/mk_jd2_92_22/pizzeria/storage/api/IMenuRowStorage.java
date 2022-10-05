package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.MenuRow;

public interface IMenuRowStorage {
    void add(MenuRow menuRow);
    void delete(MenuRow menuRow);
}
