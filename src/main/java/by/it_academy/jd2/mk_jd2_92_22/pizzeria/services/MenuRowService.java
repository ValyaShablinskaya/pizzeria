package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.MenuRowStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;

public class MenuRowService implements IMenuRowService {
    public final MenuRowStorage storage;

    public MenuRowService(MenuRowStorage storage) {
        this.storage = storage;
    }

    @Override
    public void add(MenuRow menuRow) {
        this.validate(menuRow);
        storage.add(menuRow);
    }

    @Override
    public void delete(MenuRow menuRow) {
        storage.delete(menuRow);
    }

    @Override
    public void validate(MenuRow menuRow) {
        if (menuRow == null) {
            throw new IllegalStateException("You did not transfer menu row");
        }
        if (menuRow.getInfo() == null) {
            throw new IllegalArgumentException("You did not enter info");
        }
        if (menuRow.getPrice() <= 0) {
            throw new IllegalArgumentException("Wrong pizza price");
        }
    }
}
