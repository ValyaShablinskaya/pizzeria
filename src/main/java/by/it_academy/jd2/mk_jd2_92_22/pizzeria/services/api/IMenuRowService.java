package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;

import java.util.List;

public interface IMenuRowService {
    void add(MenuRow menuRow);
    MenuRow findById(Long id);
    List<MenuRow> findAll();
    void update(Long id, MenuRow menuRow);
    void deleteById(Long id);
}
