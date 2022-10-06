package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IStage;
import lombok.*;

import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Stage implements IStage {
    private String description;
    private LocalTime time;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }
}
