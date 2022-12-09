package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IMenuService extends IEssenceService<MenuDTO>{
    MenuDTO add(MenuDTO menu);
    MenuDTO findById(Long id);
    List<MenuDTO> findAll();
    MenuDTO update(MenuDTO menu, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
}
