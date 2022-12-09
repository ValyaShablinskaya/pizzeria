package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPizzaInfoMapper {
    IPizzaInfoMapper INSTANCE = Mappers.getMapper(IPizzaInfoMapper.class);

    PizzaInfoDTO convertToPizzaInfoDTO(PizzaInfo pizzaInfo);

    PizzaInfo convertToPizzaInfo(PizzaInfoDTO pizzaInfoDTO);
    List<PizzaInfoDTO> convertToPizzaInfoList(List<PizzaInfo> pizzaInfos);
}
