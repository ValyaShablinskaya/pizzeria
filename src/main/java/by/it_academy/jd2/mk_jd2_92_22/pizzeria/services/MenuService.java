package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional(readOnly = true)
public class MenuService implements IMenuService {
    private final IMenuDao menuDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Menu is not found";

    public MenuService(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    @Transactional
    public MenuDTO create(MenuDTO menuDTO) {
        Menu menu = IMenuMapper.INSTANCE.convertToMenu(menuDTO);
        menu.setCreationDate(LocalDateTime.now());
        menu.setUpdateDate(menu.getCreationDate());
        menu = menuDao.save(menu);
        return IMenuMapper.INSTANCE.convertToMenuDTO(menu);
    }

    @Override
    public MenuDTO read(Long id) {
        Menu menu = menuDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuMapper.INSTANCE.convertToMenuDTO(menu);
    }

    @Override
    public List<MenuDTO> get() {
        List<Menu> menu = menuDao.findAll();
        return IMenuMapper.INSTANCE.convertToMenuList(menu);
    }

    @Override
    @Transactional
    public MenuDTO update(MenuDTO menuDTO, Long id, LocalDateTime updateDate) {
        Menu menuToUpdate = menuDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!menuToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Menu has been already edited");
        }
        menuToUpdate.setUpdateDate(LocalDateTime.now());
        menuToUpdate.setName(menuDTO.getName());
        menuToUpdate.setEnabled(menuDTO.isEnabled());
        menuToUpdate = menuDao.save(menuToUpdate);
       return IMenuMapper.INSTANCE.convertToMenuDTO(menuToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id, LocalDateTime updateDate) {
        Menu menuToDelete= menuDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!menuToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Menu has been already edited");
        }
        menuDao.deleteById(id);
    }
}
