package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenuRow;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
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

}