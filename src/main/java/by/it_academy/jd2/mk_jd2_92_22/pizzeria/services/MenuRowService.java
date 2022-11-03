package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class MenuRowService implements IMenuRowService {
    private final MenuRowDao menuRowDao;
    private final MenuDao menuDao;

    public MenuRowService(MenuRowDao menuRowDao, MenuDao menuDao) {
        this.menuRowDao = menuRowDao;
        this.menuDao = menuDao;
    }

    @Override
    public void add(MenuRow menuRow) {
        menuRow.setCreationDate(LocalDateTime.now());
        menuRowDao.save(menuRow);
    }

    @Override
    public MenuRow findById(Long id) {
        return menuRowDao.findById(id).orElseThrow(() -> new EntityNotFoundException("MenuRow is not found"));
    }

    @Override
    public List<MenuRow> findAll() {
        return menuRowDao.findAll();
    }

    @Override
    public void update(MenuRow menuRow, Long id, LocalDateTime updateDate) {
        MenuRow updateRow = menuRowDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("MenuRow is not found"));
        if (!updateRow.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("MenuRow has been already edited");
        }
        updateRow.setUpdateDate(LocalDateTime.now());
        updateRow.setPrice(menuRow.getPrice());
        updateRow.setPizzaInfo(menuRow.getPizzaInfo());
        menuRowDao.update(updateRow);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        MenuRow deleteRow = menuRowDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("MenuRow is not found"));
        if (!deleteRow.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("MenuRow has been already edited");
        }
        menuRowDao.deleteById(id);
    }

    @Override
    public void addRowToMenu(Long menuId, Long menuRowId) {
        if (!menuRowDao.findById(menuRowId).isPresent()) {
            throw new EntityNotFoundException("Menu row is not found");
        }
        if (!menuDao.findById(menuId).isPresent()) {
            throw new EntityNotFoundException("Menu is not found");
        }
        menuRowDao.addRowOnMenu(menuId, menuRowId);
    }

    @Override
    public List<MenuRow> findAllByIdMenu(Long id) {
        return menuRowDao.findAllRowsByIdMenu(id);
    }
}