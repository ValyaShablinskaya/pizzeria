package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IOrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_status")
public class OrderStatus implements IOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    private boolean isDone;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @ManyToMany
    @JoinTable(name = "stage_order_status",
            joinColumns = {@JoinColumn(name = "order_status_id")},
            inverseJoinColumns = {@JoinColumn(name = "stage_id")}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Stage> history = new ArrayList<>();
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
