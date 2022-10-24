package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IPizzaInfo;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PizzaInfo implements IPizzaInfo {
    private Long id;
    private String name;
    private String description;
    private Long size;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Long getSize() {
        return this.size;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
