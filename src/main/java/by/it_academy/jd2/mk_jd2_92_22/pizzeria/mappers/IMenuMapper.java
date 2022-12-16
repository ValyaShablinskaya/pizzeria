package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMenuMapper {

    MenuDTO convertToMenuDTO(Menu menu);

    Menu convertToMenu(MenuDTO menuDTO);

    List<MenuDTO> convertToMenuList(List<Menu> menus);
}
