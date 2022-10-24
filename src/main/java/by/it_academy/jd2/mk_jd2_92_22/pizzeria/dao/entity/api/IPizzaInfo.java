package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;

public interface IPizzaInfo {
    /**
     * Название пиццы
     * @return
     */
    String getName();

    /**
     * Описание/состав пиццы
     * @return
     */
    String getDescription();

    /**
     * Итоговый размер пиццы которую приготовят
     * @return
     */
    Long getSize();
    Long getId();
}
