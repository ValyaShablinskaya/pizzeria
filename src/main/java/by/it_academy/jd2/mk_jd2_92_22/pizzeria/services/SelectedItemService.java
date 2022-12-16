package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ISelectedItemDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ISelectedItemMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ISelectedItemService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.SelectedItemDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SelectedItemService implements ISelectedItemService {
    private final ISelectedItemDao selectedItemDao;
    private final IOrderDao orderDao;
    private final ISelectedItemMapper selectedItemMapper;
    private final IMenuRowMapper menuRowMapper;
    private final IOrderMapper orderMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "SelectedItem is not found";

    public SelectedItemService(ISelectedItemDao selectedItemDao, IOrderDao orderDao,
                               ISelectedItemMapper selectedItemMapper, IMenuRowMapper menuRowMapper,
                               IOrderMapper orderMapper) {
        this.selectedItemDao = selectedItemDao;
        this.orderDao = orderDao;
        this.selectedItemMapper = selectedItemMapper;
        this.menuRowMapper = menuRowMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public SelectedItemDTO create(SelectedItemDTO selectedItemDTO) {
        SelectedItem selectedItem = selectedItemMapper.convertToSelectedItem(selectedItemDTO);
        selectedItem.setCreationDate(LocalDateTime.now());
        selectedItem.setUpdateDate(selectedItem.getCreationDate());
        selectedItem = selectedItemDao.save(selectedItem);
        return selectedItemMapper.convertToSelectedItemDTO(selectedItem);
    }

    @Override
    public SelectedItemDTO read(Long id) {
        SelectedItem selectedItem = selectedItemDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return selectedItemMapper.convertToSelectedItemDTO(selectedItem);
    }

    @Override
    public List<SelectedItemDTO> get() {
        List<SelectedItem> selectedItems = selectedItemDao.findAll();
        return selectedItemMapper.convertToSelectedItemList(selectedItems);
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
        MenuRow menuRow = menuRowMapper.convertToMenuRow(selectedItemDTO.getMenuRow());
        updateSelectedItem.setMenuRow(menuRow);
        Order order = orderMapper.convertToOrder(selectedItemDTO.getOrder());
        updateSelectedItem.setOrder(order);
        updateSelectedItem = selectedItemDao.save(updateSelectedItem);
        return selectedItemMapper.convertToSelectedItemDTO(updateSelectedItem);
    }

    @Override
    public void delete(Long id, LocalDateTime updateDate) {
        SelectedItem deleteSelectedItem = selectedItemDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteSelectedItem.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("SelectedItem has been already edited");
        }
        selectedItemDao.deleteById(id);
    }
}
