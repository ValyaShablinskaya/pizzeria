package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IDoneOrder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class DoneOrder implements IDoneOrder {
    private Long id;
    private Ticket ticket;
    private List<Pizza> pizzas;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public List<Pizza> getItems() {
        return this.pizzas;
    }
}
