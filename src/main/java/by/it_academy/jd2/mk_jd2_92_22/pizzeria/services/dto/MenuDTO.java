package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MenuDTO {
    private String name;
    private List<MenuRowDTO> menuRows;
    private boolean enabled;
}
