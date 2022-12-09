package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.SelectedItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ISelectedItemMapper {
    ISelectedItemMapper INSTANCE = Mappers.getMapper(ISelectedItemMapper.class);

    SelectedItemDTO convertToSelectedItemDTO(SelectedItem selectedItem);

    SelectedItem convertToSelectedItem(SelectedItemDTO selectedItemDTO);
    List<SelectedItemDTO> convertToSelectedItemList(List<SelectedItem> selectedItems);
}
