package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;


import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class MenuService implements IMenuService {
    private final IMenuDao menuDao;
    private final IMenuMapper menuMapper;
    private final IMenuRowMapper menuRowMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Menu is not found";

    public MenuService(IMenuDao menuDao, IMenuMapper menuMapper, IMenuRowMapper menuRowMapper) {
        this.menuDao = menuDao;
        this.menuMapper = menuMapper;
        this.menuRowMapper = menuRowMapper;
    }

    @Override
    @Transactional
    public MenuDTO create(MenuDTO menuDTO) {
        Menu menu = menuMapper.convertToMenu(menuDTO);
        menu.setCreationDate(LocalDateTime.now());
        menu.setUpdateDate(menu.getCreationDate());
        menu = menuDao.save(menu);
        return menuMapper.convertToMenuDTO(menu);
    }

    @Override
    public MenuDTO read(Long id) {
        Menu menu = menuDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return menuMapper.convertToMenuDTO(menu);
    }

    @Override
    public List<MenuDTO> get() {
        List<Menu> menu = menuDao.findAll();
        return menuMapper.convertToMenuList(menu);
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
        List<MenuRow> menuRows = menuRowMapper.convertToMenuRowListFromDto(menuDTO.getMenuRows());
        menuToUpdate.setMenuRows(menuRows);
        menuToUpdate = menuDao.save(menuToUpdate);

       return menuMapper.convertToMenuDTO(menuToUpdate);
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
