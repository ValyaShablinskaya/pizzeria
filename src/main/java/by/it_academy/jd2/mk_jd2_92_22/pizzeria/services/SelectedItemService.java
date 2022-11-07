package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.SelectedItemDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ISelectedItemService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class SelectedItemService implements ISelectedItemService {
    private final SelectedItemDao selectedItemDao;
    private final OrderDao orderDao;

    public SelectedItemService(SelectedItemDao selectedItemDao, OrderDao orderDao) {
        this.selectedItemDao = selectedItemDao;
        this.orderDao = orderDao;
    }

    @Override
    public void add(SelectedItem selectedItem) {
        selectedItem.setCreationDate(LocalDateTime.now());
        selectedItemDao.save(selectedItem);
    }

    @Override
    public SelectedItem findById(Long id) {
        return selectedItemDao.findById(id).orElseThrow(() -> new EntityNotFoundException("SelectedItem is not found"));
    }

    @Override
    public List<SelectedItem> findAll() {
        return selectedItemDao.findAll();
    }

    @Override
    public void update(SelectedItem selectedItem, Long id, LocalDateTime updateDate) {
        SelectedItem updateSelectedItem = selectedItemDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("SelectedItem is not found"));
        if (!updateSelectedItem.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("SelectedItem has been already edited");
        }
        updateSelectedItem.setUpdateDate(LocalDateTime.now());
        updateSelectedItem.setCount(selectedItem.getCount());
        updateSelectedItem.setMenuRow(selectedItem.getMenuRow());
        updateSelectedItem.setOrder(selectedItem.getOrder());
        selectedItemDao.update(updateSelectedItem);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        SelectedItem deleteSelectedItem = selectedItemDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("SelectedItem is not found"));
        if (!deleteSelectedItem.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("SelectedItem has been already edited");
        }
        selectedItemDao.deleteById(id);
    }

    @Override
    public List<SelectedItem> findAllByIdOrder(Long id) {
        return selectedItemDao.findAllSelectedItemByIdOrder(id);
    }
}
