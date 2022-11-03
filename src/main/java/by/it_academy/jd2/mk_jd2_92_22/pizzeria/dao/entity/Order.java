package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IOrder;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Order implements IOrder {
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    private List<SelectedItem> selectedItems;

    @Override
    public List<SelectedItem> getSelected() {
        return this.selectedItems;
    }
}

