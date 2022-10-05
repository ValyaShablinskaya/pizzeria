package by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.api;
/**
 * Выбор покупателя
 */
public interface ISelectedItem {

    /**
     * Выбранное из меню
     * @return
     */
    IMenuRow getRow();

    /**
     * Количество выбранного
     * @return
     */
    int getCount();
}
