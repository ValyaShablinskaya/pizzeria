package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.api.IMenuStorage;

import java.util.ArrayList;
import java.util.List;

public class MenuStorage implements IMenuStorage {
    public List<MenuRow> data = new ArrayList<>();

    @Override
    public List<MenuRow> findAll() {
        return this.data;
    }

    @Override
    public void add(MenuRow menuRow) {
        this.data.add(menuRow);
    }

    @Override
    public void delete(MenuRow menuRow) {
        this.data.remove(menuRow);
    }
}
