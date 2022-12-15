package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenuRow;

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
@Table(name = "menu_row")
public class MenuRow implements IMenuRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "pizza_info_id")
    private PizzaInfo pizzaInfo;
    private Double price;
    @ManyToMany
    @JoinTable(name = "menu_menu_row",
            joinColumns = {@JoinColumn(name = "menu_row_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id")}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Menu> menus = new ArrayList<>();
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Override
    public PizzaInfo getInfo() {
        return this.pizzaInfo;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void addMenuToMenuRow(Menu menu) {
        this.menus.add(menu);
    }
}