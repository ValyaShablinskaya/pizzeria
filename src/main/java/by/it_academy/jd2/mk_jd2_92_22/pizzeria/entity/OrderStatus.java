package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IOrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.IStage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api.ITicket;
import lombok.Builder;

import java.util.List;

@Builder
public class OrderStatus implements IOrderStatus {
    private final ITicket ticket;
    private final List<IStage> history;
    private boolean isDone;

    @Override
    public ITicket getTicket() {
        return ticket;
    }

    @Override
    public List<IStage> getHistory() {
        return this.history;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }
}
