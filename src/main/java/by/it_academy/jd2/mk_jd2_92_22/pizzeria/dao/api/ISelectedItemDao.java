package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;

import java.util.List;

public interface ISelectedItemDao extends ICrudDao<SelectedItem, Long>{
    List<SelectedItem> findAllSelectedItemByIdOrder(Long id);
}
