package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPizzaMapper {
    IPizzaMapper INSTANCE = Mappers.getMapper(IPizzaMapper.class);

    PizzaDTO convertToPizzaDTO(Pizza pizza);

    Pizza convertToPizza(PizzaDTO pizzaDTO);
    List<PizzaDTO> convertToPizzaList(List<Pizza> pizzas);
}
