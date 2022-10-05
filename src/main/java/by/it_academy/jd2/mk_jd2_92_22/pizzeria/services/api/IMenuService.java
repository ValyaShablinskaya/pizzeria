package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;

import java.util.List;

public interface IMenuService {
    List<MenuRow> findAll();
}
