package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;

/**
 * Пиццерия
 */
public interface IPizzeria {

    /**
     * Получить меню
     * @return меню с доступными для заказа пиццами
     */
    Menu takeMenu();

    /**
     * Оформить заказ
     * @param order заказ
     * @return квиток для отслеживания
     */
    Ticket create(Order order);

    /**
     * Проверить состояние заказа по квитку
     * @param ticket квиток выданный при создании заказа
     * @return информация о состоянии заказа
     */
    OrderStatus check(Ticket ticket);

    /**
     * Получить сформированный заказ
     * @param ticket квиток выданный при создании заказа
     * @return готовый заказ с тем что мы выбрали
     */
    DoneOrder pickup(Ticket ticket);
}
