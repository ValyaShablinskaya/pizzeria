package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderStatusDTO {
    private TicketDTO ticket;
    private boolean isDone;
    private List<StageDTO> history;
}
