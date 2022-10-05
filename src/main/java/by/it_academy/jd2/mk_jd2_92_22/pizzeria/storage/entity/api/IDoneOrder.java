package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.api;

import java.util.List;

/**
 * Готовый заказ
 */
public interface IDoneOrder {

    /**
     * Квиток по которому заказ готовился
     * @return квиток выданный при формировании заказа
     */
    ITicket getTicket();

    /**
     * Готовые пиццы приготовленные по заказу
     * @return список пицц
     */
    List<IPizza> getItems();
}
