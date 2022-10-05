package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.ITicket;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Ticket implements ITicket {
    private final String number;
    private final LocalDateTime creatAt;
    private final IOrder order;

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public LocalDateTime getCreatAt() {
        return this.creatAt;
    }

    @Override
    public IOrder getOrder() {
        return this.order;
    }
}
