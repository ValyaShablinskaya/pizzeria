package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;

import java.time.LocalDateTime;
import java.util.List;

public interface ISelectedItemService {
    void add(SelectedItem selectedItem);
    SelectedItem findById(Long id);
    List<SelectedItem> findAll();
    void update(SelectedItem selectedItem, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
    List<SelectedItem> findAllByIdOrder(Long id);
}
