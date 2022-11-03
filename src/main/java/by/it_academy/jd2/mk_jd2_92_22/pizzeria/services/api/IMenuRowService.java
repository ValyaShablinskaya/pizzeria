package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;

import java.time.LocalDateTime;
import java.util.List;

public interface IMenuRowService {
    void add(MenuRow menuRow);
    MenuRow findById(Long id);
    List<MenuRow> findAll();
    void update(MenuRow menuRow, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
    void  addRowToMenu(Long menuId, Long menuRowId);
    List<MenuRow> findAllByIdMenu(Long id);
}
