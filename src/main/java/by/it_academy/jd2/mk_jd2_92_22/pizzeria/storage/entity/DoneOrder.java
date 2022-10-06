package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IDoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IPizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.ITicket;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DoneOrder implements IDoneOrder {
    private ITicket ticket;
    private List<IPizza> pizzas;

    @Override
    public ITicket getTicket() {
        return ticket;
    }

    @Override
    public List<IPizza> getItems() {
        return this.pizzas;
    }
}
