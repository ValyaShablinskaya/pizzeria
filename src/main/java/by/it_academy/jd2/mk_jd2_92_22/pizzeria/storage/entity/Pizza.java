package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IPizza;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pizza implements IPizza {
    private String name;
    private int size;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
