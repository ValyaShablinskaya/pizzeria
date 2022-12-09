package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService extends IEssenceService<OrderDTO> {
    OrderDTO add(OrderDTO order);
    OrderDTO findById(Long id);
    List<OrderDTO> findAll();
    OrderDTO update(OrderDTO order, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
}
