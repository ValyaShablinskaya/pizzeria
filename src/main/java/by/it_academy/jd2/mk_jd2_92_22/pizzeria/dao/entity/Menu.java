package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api.IMenu;
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
@Table(name = "menu")
public class Menu implements IMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MenuRow> menuRows = new ArrayList<>();
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    private boolean enabled;

    @Override
    public List<MenuRow> getItems() {
        return this.menuRows;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
