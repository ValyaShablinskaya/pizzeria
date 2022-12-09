package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.DoneOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IDoneOrderMapper {
    IDoneOrderMapper INSTANCE = Mappers.getMapper(IDoneOrderMapper.class);

    DoneOrderDTO convertToDoneOrderDTO(DoneOrder doneOrder);

    DoneOrder convertToDoneOrder(DoneOrderDTO doneOrderDTO);
    List<DoneOrderDTO> convertToDoneOrderList(List<DoneOrder> doneOrders);
}
