package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;

import java.util.List;

public interface IMenuRowDao extends ICrudDao<MenuRow, Long> {
    void  addRowOnMenu(Long menuId, Long menuRowId);
    List<MenuRow> findAllRowsByIdMenu(Long id);
}
