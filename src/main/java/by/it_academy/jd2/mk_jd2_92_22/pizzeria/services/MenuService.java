package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.MenuRowStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.MenuStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;

import java.util.List;

public class MenuService implements IMenuService {
    private final MenuStorage storage;

    public MenuService(MenuStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<MenuRow> findAll() {
        return this.storage.findAll();
    }
}
