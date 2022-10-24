package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenuRow;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MenuRow implements IMenuRow {
    private Long id;
    private PizzaInfo pizzaInfo;
    private Double price;
    private LocalDateTime creationDate;
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
