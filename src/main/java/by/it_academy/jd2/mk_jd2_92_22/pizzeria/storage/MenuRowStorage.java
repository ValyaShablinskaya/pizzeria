package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.api.IMenuRowStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;

import java.util.ArrayList;
import java.util.List;

public class MenuRowStorage implements IMenuRowStorage {
    List<MenuRow> data = new ArrayList<>();
    @Override
    public void add(MenuRow menuRow) {
        this.data.add(menuRow);
    }

    @Override
    public void delete(MenuRow menuRow) {
        this.data.remove(menuRow);
    }
}
