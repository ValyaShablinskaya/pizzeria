package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.ITicket;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Ticket implements ITicket {
    private Long id;
    private Order order;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    @Override
    public LocalDateTime getCreatAt() {
        return this.creationDate;
    }

    @Override
    public Order getOrder() {
        return this.order;
    }
}
