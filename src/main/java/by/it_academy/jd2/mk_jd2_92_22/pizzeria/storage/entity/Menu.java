package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenuRow;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Menu implements IMenu {
    private List<IMenuRow> menuRows;

    @Override
    public List<IMenuRow> getItems() {
        return this.menuRows;
    }
}
