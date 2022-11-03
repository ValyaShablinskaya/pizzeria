package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.api;

import java.time.LocalTime;

/**
 * Описание этапа выполнения заказа
 */
public interface IStage {
    /**
     * Описание этапа
     * @return
     */
    String getDescription();

    /**
     * Когда этап был начат
     * @return
     */
    LocalTime getTime();
}
