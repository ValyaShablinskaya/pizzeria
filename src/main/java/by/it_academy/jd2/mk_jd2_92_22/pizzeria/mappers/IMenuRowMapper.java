package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuRowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IMenuRowMapper {
    IMenuRowMapper INSTANCE = Mappers.getMapper(IMenuRowMapper.class);

    MenuRowDTO convertToMenuRowDTO(MenuRow menuRow);

    MenuRow convertToMenuRow(MenuRowDTO menuRowDTO);
    List<MenuRowDTO> convertToMenuRowList(List<MenuRow> menuRows);
}
