package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;

import java.util.List;

/**
 * Заказ сформированный покупателем
 */
public interface IOrder {
    /**
     * Список выбранного для заказа
     * @return список выбранного
     */
    List<SelectedItem> getSelected();
}
