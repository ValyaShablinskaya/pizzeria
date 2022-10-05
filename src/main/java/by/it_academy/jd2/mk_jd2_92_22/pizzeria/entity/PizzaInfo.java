package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IPizzaInfo;
import lombok.Builder;

@Builder
public class PizzaInfo implements IPizzaInfo {
    private final String name;
    private final String description;
    private final int size;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
