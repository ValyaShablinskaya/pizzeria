package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IStage;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Stage implements IStage {
    private Long id;
    private String description;
    private LocalTime time;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }
}

