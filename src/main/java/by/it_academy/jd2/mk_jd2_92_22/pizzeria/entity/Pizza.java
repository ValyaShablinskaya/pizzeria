package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IPizza;
import lombok.Builder;

@Builder
public class Pizza implements IPizza {
    private final String name;
    private final int size;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
