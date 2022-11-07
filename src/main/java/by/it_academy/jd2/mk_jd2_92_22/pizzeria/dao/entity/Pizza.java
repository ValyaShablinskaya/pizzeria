package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IPizza;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Pizza implements IPizza {
    private Long id;
    private String name;
    private Long size;
    private DoneOrder doneOrder;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Long getSize() {
        return this.size;
    }
}
