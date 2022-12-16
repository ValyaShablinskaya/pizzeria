package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IStage;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stage")
public class Stage implements IStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderStatus orderStatus;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public LocalTime getTime() {
        return this.time;
    }
}

