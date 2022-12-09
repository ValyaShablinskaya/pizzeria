package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IOrderMapper {
    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    OrderDTO convertToOrderDTO(Order order);

    Order convertToOrder(OrderDTO orderDTO);
    List<OrderDTO> convertToOrderList(List<Order> orders);
}
