package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.ISelectedItem;
import lombok.Builder;

import java.util.List;

@Builder
public class Order implements IOrder {
    private final List<ISelectedItem> selectedItems;

    @Override
    public List<ISelectedItem> getSelected() {
        return this.selectedItems;
    }
}
