package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDTO {
    private OrderDTO order;
}
