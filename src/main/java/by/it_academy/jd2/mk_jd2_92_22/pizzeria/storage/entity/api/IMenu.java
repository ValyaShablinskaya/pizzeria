package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api;

import java.util.List;

/**
 * Меню
 */
public interface IMenu {

    /**
     * Доступные к заказу пункты
     * @return пункты которые можно заказать
     */
    List<IMenuRow> getItems();
}
