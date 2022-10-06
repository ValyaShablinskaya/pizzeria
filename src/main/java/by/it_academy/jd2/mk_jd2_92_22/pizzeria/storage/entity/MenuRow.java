package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IPizzaInfo;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MenuRow implements IMenuRow {
    private IPizzaInfo pizzaInfo;
    private double price;

    @Override
    public IPizzaInfo getInfo() {
        return this.pizzaInfo;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
