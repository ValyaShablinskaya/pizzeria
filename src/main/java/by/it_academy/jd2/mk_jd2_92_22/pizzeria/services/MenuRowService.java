package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class MenuRowService implements IMenuRowService {
    private final MenuRowDao menuRowDao;

    public MenuRowService(MenuRowDao menuRowDao) {
        this.menuRowDao = menuRowDao;
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
    public void update(Long id, MenuRow menuRow) {
        MenuRow updateRow = menuRowDao.findById(id).orElseThrow(() -> new EntityNotFoundException("MenuRow is not found"));
        updateRow.setPrice(menuRow.getPrice());
        updateRow.setUpdateDate(LocalDateTime.now());
        menuRowDao.update(updateRow);
    }

    @Override
    public void deleteById(Long id) {
        if (!menuRowDao.findById(id).isPresent()) {
            throw new EntityNotFoundException("MenuRow is not found");
        }
       menuRowDao.deleteById(id);
    }
}
