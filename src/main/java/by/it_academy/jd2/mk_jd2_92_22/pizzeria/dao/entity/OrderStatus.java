package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IOrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class OrderStatus implements IOrderStatus {
    private Long id;
    private Ticket ticket;
    private boolean isDone;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    private List<Stage> history;
    @Override
    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public List<Stage> getHistory() {
        return this.history;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }
}
