package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderStatusDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderStatusService extends IEssenceService<OrderStatusDTO> {
    OrderStatusDTO add(OrderStatusDTO orderStatus);
    OrderStatusDTO findById(Long id);
    List<OrderStatusDTO> findAll();
    OrderStatusDTO update(OrderStatusDTO orderStatus, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
}
