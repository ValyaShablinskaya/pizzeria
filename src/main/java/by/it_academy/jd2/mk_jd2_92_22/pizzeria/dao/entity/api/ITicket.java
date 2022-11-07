package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;

import java.time.LocalDateTime;

/**
 * Квиток выдаваемый к заказу
 */
public interface ITicket {

    /**
     * Когда заказ получен
     * @return
     */
    LocalDateTime getCreatAt();

    /**
     * Заказ для которого выдали квиток
     * @return
     */
    Order getOrder();
}
