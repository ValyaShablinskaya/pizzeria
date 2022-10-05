package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api;
/**
 * Строчка меню
 */
public interface IMenuRow {

    /**
     * Информация о пицце
     * @return
     */
    IPizzaInfo getInfo();

    /**
     * Стоимость пиццы
     * @return
     */
    double getPrice();
}
