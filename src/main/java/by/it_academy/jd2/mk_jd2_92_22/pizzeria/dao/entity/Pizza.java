package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IPizza;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pizza")
public class Pizza implements IPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long size;
    @ManyToOne
    @JoinColumn(name = "done_order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private DoneOrder doneOrder;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Long getSize() {
        return this.size;
    }
}
