package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPizzaMapper {

    PizzaDTO convertToPizzaDTO(Pizza pizza);

    Pizza convertToPizza(PizzaDTO pizzaDTO);
    List<PizzaDTO> convertToPizzaList(List<Pizza> pizzas);
    List<Pizza> convertToPizzaListFromDto(List<PizzaDTO> pizzas);
}
