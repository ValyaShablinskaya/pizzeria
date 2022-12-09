package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.SelectedItemDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ISelectedItemMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ISelectedItemService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.SelectedItemDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class SelectedItemService implements ISelectedItemService {
    private final SelectedItemDao selectedItemDao;
    private final OrderDao orderDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "SelectedItem is not found";

    public SelectedItemService(SelectedItemDao selectedItemDao, OrderDao orderDao) {
        this.selectedItemDao = selectedItemDao;
        this.orderDao = orderDao;
    }

    @Override
    public SelectedItemDTO add(SelectedItemDTO selectedItemDTO) {
        SelectedItem selectedItem = ISelectedItemMapper.INSTANCE.convertToSelectedItem(selectedItemDTO);
        selectedItem.setCreationDate(LocalDateTime.now());
        selectedItem.setUpdateDate(selectedItem.getCreationDate());
        selectedItem = selectedItemDao.save(selectedItem).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ISelectedItemMapper.INSTANCE.convertToSelectedItemDTO(selectedItem);
    }

    @Override
    public SelectedItemDTO findById(Long id) {
        SelectedItem selectedItem = selectedItemDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ISelectedItemMapper.INSTANCE.convertToSelectedItemDTO(selectedItem);
    }

    @Override
    public List<SelectedItemDTO> findAll() {
        List<SelectedItem> selectedItems = selectedItemDao.findAll();
        return ISelectedItemMapper.INSTANCE.convertToSelectedItemList(selectedItems);
    }

    @Override
    public SelectedItemDTO update(SelectedItemDTO selectedItemDTO, Long id, LocalDateTime updateDate) {
        SelectedItem updateSelectedItem = selectedItemDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateSelectedItem.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("SelectedItem has been already edited");
        }
        updateSelectedItem.setUpdateDate(LocalDateTime.now());
        updateSelectedItem.setCount(selectedItemDTO.getCount());
        MenuRow menuRow = IMenuRowMapper.INSTANCE.convertToMenuRow(selectedItemDTO.getMenuRow());
        updateSelectedItem.setMenuRow(menuRow);
        Order order = IOrderMapper.INSTANCE.convertToOrder(selectedItemDTO.getOrder());
        updateSelectedItem.setOrder(order);
        updateSelectedItem = selectedItemDao.update(updateSelectedItem).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return ISelectedItemMapper.INSTANCE.convertToSelectedItemDTO(updateSelectedItem);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        SelectedItem deleteSelectedItem = selectedItemDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteSelectedItem.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("SelectedItem has been already edited");
        }
        selectedItemDao.deleteById(id);
    }

    @Override
    public List<SelectedItemDTO> findAllByIdOrder(Long id) {
        List<SelectedItem> selectedItems = selectedItemDao.findAllSelectedItemByIdOrder(id);
        return ISelectedItemMapper.INSTANCE.convertToSelectedItemList(selectedItems);
    }
}
