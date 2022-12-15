package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IOrder;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order implements IOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<SelectedItem> selectedItems = new ArrayList<>();

    @Override
    public List<SelectedItem> getSelected() {
        return this.selectedItems;
    }
}

