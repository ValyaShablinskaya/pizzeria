package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusDao extends JpaRepository<OrderStatus, Long> {

}
