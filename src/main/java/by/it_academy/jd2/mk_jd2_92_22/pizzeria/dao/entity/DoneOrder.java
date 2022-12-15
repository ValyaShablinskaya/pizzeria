package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IDoneOrder;
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
@Table(name = "done_order")
public class DoneOrder implements IDoneOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Pizza> pizzas = new ArrayList<>();
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Override
    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public List<Pizza> getItems() {
        return this.pizzas;
    }
}
