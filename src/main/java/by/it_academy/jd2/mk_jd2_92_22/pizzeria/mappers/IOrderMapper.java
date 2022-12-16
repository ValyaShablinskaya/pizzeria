package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderMapper {

    OrderDTO convertToOrderDTO(Order order);

    Order convertToOrder(OrderDTO orderDTO);
    List<OrderDTO> convertToOrderList(List<Order> orders);
}
