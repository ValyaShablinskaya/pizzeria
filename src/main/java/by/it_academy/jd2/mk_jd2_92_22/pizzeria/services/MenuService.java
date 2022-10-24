package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class MenuService implements IMenuService {
    private final MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public void add(Menu menu) {
        menu.setCreationDate(LocalDateTime.now());
        menuDao.save(menu);
    }

    @Override
    public Menu findById(Long id) {
        return menuDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Menu is not found"));
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public void update(Menu menu) {
        menu.setUpdateDate(LocalDateTime.now());
        if (!menuDao.findById(menu.getId()).isPresent()) {
            throw new EntityNotFoundException("Menu is not found");
        }
        menuDao.update(menu);
    }

    @Override
    public void deleteById(Long id) {
        if (!menuDao.findById(id).isPresent()) {
            throw new EntityNotFoundException("Menu is not found");
        }
        menuDao.deleteById(id);
    }
}
