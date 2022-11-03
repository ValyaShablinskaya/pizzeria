package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;

/**
 * Выбор покупателя
 */
public interface ISelectedItem {
    /**
     * Выбранное из меню
     * @return
     */
    MenuRow getRow();

    /**
     * Количество выбранного
     * @return
     */
    Long getCount();
}
