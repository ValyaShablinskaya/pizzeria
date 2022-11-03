package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.ISelectedItem;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class SelectedItem implements ISelectedItem {
    private Long id;
    private MenuRow menuRow;
    private Order order;
    private Long count;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public MenuRow getRow() {
        return this.menuRow;
    }

    @Override
    public Long getCount() {
        return this.count;
    }
}
