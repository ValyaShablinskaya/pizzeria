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
    public void update(Menu menu, Long id, LocalDateTime updateDate) {
        Menu menuToUpdate = menuDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Menu is not found"));
        if (!menuToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Menu has been already edited");
        }
        menuToUpdate.setUpdateDate(LocalDateTime.now());
        menuToUpdate.setName(menu.getName());
        menuToUpdate.setEnabled(menu.isEnabled());

        menuDao.update(menuToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Menu menuToDelete= menuDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Menu is not found"));
        if (!menuToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Menu has been already edited");
        }
        menuDao.deleteById(id);
    }
}
