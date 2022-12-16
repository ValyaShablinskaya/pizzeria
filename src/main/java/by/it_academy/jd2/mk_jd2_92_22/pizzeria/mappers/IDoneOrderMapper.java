package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.DoneOrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDoneOrderMapper {

    DoneOrderDTO convertToDoneOrderDTO(DoneOrder doneOrder);

    DoneOrder convertToDoneOrder(DoneOrderDTO doneOrderDTO);
    List<DoneOrderDTO> convertToDoneOrderList(List<DoneOrder> doneOrders);
}
