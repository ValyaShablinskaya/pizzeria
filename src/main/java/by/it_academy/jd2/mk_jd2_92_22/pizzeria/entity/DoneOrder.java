package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IDoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IPizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.ITicket;
import lombok.Builder;

import java.util.List;

@Builder
public class DoneOrder implements IDoneOrder {
    private final ITicket ticket;
    private final List<IPizza> pizzas;

    @Override
    public ITicket getTicket() {
        return ticket;
    }

    @Override
    public List<IPizza> getItems() {
        return this.pizzas;
    }
}
