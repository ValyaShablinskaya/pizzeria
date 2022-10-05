package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenuRow;
import lombok.Builder;

import java.util.List;

@Builder
public class Menu implements IMenu {
    private final List<IMenuRow> menuRows;

    @Override
    public List<IMenuRow> getItems() {
        return this.menuRows;
    }
}
