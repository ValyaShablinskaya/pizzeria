package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PizzaDTO {
    private String name;
    private Long size;
    private DoneOrderDTO doneOrder;
}
