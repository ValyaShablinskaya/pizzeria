package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuRowDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IMenuRowService extends IEssenceService<MenuRowDTO>{
    MenuRowDTO add(MenuRowDTO menuRow);
    MenuRowDTO findById(Long id);
    List<MenuRowDTO> findAll();
    MenuRowDTO update(MenuRowDTO menuRow, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
    void  addRowToMenu(Long menuId, Long menuRowId);
    List<MenuRowDTO> findAllByIdMenu(Long id);
}
