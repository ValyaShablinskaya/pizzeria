package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IMenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IPizzaInfo;
import lombok.Builder;

@Builder
public class MenuRow implements IMenuRow {
    private final IPizzaInfo pizzaInfo;
    private final double price;

    @Override
    public IPizzaInfo getInfo() {
        return this.pizzaInfo;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
