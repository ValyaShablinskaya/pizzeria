package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderStatusDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderStatusMapper {

   OrderStatusDTO convertToOrderStatusDTO(OrderStatus orderStatus);

    OrderStatus convertToOrderStatus(OrderStatusDTO orderStatusDTO);
    List<OrderStatusDTO> convertToOrderStatusList(List<OrderStatus> orderStatuses);
}
