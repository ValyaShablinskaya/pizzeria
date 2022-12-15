package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.ISelectedItem;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "selected_item")
public class SelectedItem implements ISelectedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "menu_row_id")
    private MenuRow menuRow;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;
    private Long count;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
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
