package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IPizzaInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pizza_info")
public class PizzaInfo implements IPizzaInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long size;
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
    public String getDescription() {
        return this.description;
    }

    @Override
    public Long getSize() {
        return this.size;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
