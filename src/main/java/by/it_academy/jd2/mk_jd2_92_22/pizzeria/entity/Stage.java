package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IStage;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public class Stage implements IStage {
    private final String description;
    private final LocalTime time;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }
}
