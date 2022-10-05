package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api;

import java.time.LocalDateTime;

/**
 * Квиток выдаваемый к заказу
 */
public interface ITicket {

    /**
     * Уникальный номер заказа
     * @return
     */
    String getNumber();

    /**
     * Когда заказ получен
     * @return
     */
    LocalDateTime getCreatAt();

    /**
     * Заказ для которого выдали квиток
     * @return
     */
    IOrder getOrder();
}
