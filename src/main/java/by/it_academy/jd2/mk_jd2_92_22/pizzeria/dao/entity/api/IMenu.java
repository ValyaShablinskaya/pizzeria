package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;

import java.util.List;

public interface IMenu {
    /**
     * Доступные к заказу пункты
     * @return пункты которые можно заказать
     */
    List<MenuRow> getItems();
}

