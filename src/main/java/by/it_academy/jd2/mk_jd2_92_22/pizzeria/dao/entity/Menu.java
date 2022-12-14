package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenu;
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
    private String name;
    private List<MenuRow> menuRows;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private boolean enabled;

    @Override
    public List<MenuRow> getItems() {
        return this.menuRows;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
