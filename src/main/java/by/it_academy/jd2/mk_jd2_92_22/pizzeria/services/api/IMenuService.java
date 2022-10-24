package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;

import java.util.List;

public interface IMenuService {
    void add(Menu menu);
    Menu findById(Long id);
    List<Menu> findAll();
    void update(Menu menu);
    void deleteById(Long id);
}
