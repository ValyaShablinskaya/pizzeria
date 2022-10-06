package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.ISelectedItem;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Order implements IOrder {
    private List<ISelectedItem> selectedItems;

    @Override
    public List<ISelectedItem> getSelected() {
        return this.selectedItems;
    }
}
