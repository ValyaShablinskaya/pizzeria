package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class MenuService implements IMenuService {
    private final MenuDao menuDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Menu is not found";

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public MenuDTO add(MenuDTO menuDTO) {
        Menu menu = IMenuMapper.INSTANCE.convertToMenu(menuDTO);
        menu.setCreationDate(LocalDateTime.now());
        menu.setUpdateDate(menu.getCreationDate());
        menu = menuDao.save(menu).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuMapper.INSTANCE.convertToMenuDTO(menu);
    }

    @Override
    public MenuDTO findById(Long id) {
        Menu menu = menuDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuMapper.INSTANCE.convertToMenuDTO(menu);
    }

    @Override
    public List<MenuDTO> findAll() {
        List<Menu> menu = menuDao.findAll();
        return IMenuMapper.INSTANCE.convertToMenuList(menu);
    }

    @Override
    public MenuDTO update(MenuDTO menuDTO, Long id, LocalDateTime updateDate) {
        Menu menuToUpdate = menuDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!menuToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Menu has been already edited");
        }
        menuToUpdate.setUpdateDate(LocalDateTime.now());
        menuToUpdate.setName(menuDTO.getName());
        menuToUpdate.setEnabled(menuDTO.isEnabled());
        menuToUpdate = menuDao.update(menuToUpdate).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
       return IMenuMapper.INSTANCE.convertToMenuDTO(menuToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Menu menuToDelete= menuDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!menuToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Menu has been already edited");
        }
        menuDao.deleteById(id);
    }
}
