package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.ITicket;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticket")
public class Ticket implements ITicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "orders_id")
    private Order order;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
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
