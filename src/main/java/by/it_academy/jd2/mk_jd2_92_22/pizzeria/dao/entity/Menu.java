package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenuRow;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Menu implements IMenu {
    private Long id;
    private IMenuRow menuRow;
    private List<IMenuRow> menuRows;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public List<IMenuRow> getItems() {
        return this.menuRows;
    }
}
