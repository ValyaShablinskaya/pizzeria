package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPizzaInfoMapper {

    PizzaInfoDTO convertToPizzaInfoDTO(PizzaInfo pizzaInfo);

    PizzaInfo convertToPizzaInfo(PizzaInfoDTO pizzaInfoDTO);
    List<PizzaInfoDTO> convertToPizzaInfoList(List<PizzaInfo> pizzaInfos);
}
