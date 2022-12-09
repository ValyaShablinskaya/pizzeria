package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.SelectedItemDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ISelectedItemService extends IEssenceService<SelectedItemDTO>{
    SelectedItemDTO add(SelectedItemDTO selectedItem);
    SelectedItemDTO findById(Long id);
    List<SelectedItemDTO> findAll();
    SelectedItemDTO update(SelectedItemDTO selectedItem, Long id, LocalDateTime updateDate);
    void deleteById(Long id, LocalDateTime updateDate);
    List<SelectedItemDTO> findAllByIdOrder(Long id);
}
