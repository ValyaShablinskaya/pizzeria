package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IOrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.IStage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api.ITicket;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderStatus implements IOrderStatus {
    private Ticket ticket;
    private List<IStage> history;
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
