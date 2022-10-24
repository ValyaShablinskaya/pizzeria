package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;

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
    Double getPrice();
    Long getId();
}
