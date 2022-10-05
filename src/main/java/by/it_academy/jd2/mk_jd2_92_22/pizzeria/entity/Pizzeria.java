package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.*;
import lombok.Builder;

@Builder
public class Pizzeria implements IPizzeria {
    private final IMenu menu;
    private final ITicket ticket;
    private final IOrderStatus orderStatus;
    private final IDoneOrder doneOrder;

    @Override
    public IMenu takeMenu() {
        return this.menu;
    }

    @Override
    public ITicket create(IOrder order) {
        return this.ticket;
    }

    @Override
    public IOrderStatus check(ITicket ticket) {
        return this.orderStatus;
    }

    @Override
    public IDoneOrder pickup(ITicket ticket) {
        return this.doneOrder;
    }
}
