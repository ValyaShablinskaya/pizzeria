package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuRowDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class MenuRowService implements IMenuRowService {
    private final IMenuRowDao menuRowDao;
    private final IMenuDao menuDao;
    private final IMenuRowMapper menuRowMapper;
    private final IPizzaInfoMapper pizzaInfoMapper;
    private final IMenuMapper menuMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "MenuRow is not found";

    public MenuRowService(IMenuRowDao menuRowDao, IMenuDao menuDao, IMenuRowMapper menuRowMapper,
                          IPizzaInfoMapper pizzaInfoMapper, IMenuMapper menuMapper) {
        this.menuRowDao = menuRowDao;
        this.menuDao = menuDao;
        this.menuRowMapper = menuRowMapper;
        this.pizzaInfoMapper = pizzaInfoMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    @Transactional
    public MenuRowDTO create(MenuRowDTO menuRowDTO) {
        MenuRow menuRow = menuRowMapper.convertToMenuRow(menuRowDTO);
        menuRow.setCreationDate(LocalDateTime.now());
        menuRow.setUpdateDate(menuRow.getCreationDate());
        menuRow = menuRowDao.save(menuRow);
        return menuRowMapper.convertToMenuRowDTO(menuRow);
    }

    @Override
    public MenuRowDTO read(Long id) {
        MenuRow menuRow = menuRowDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return menuRowMapper.convertToMenuRowDTO(menuRow);
    }

    @Override
    public List<MenuRowDTO> get() {
        List<MenuRow> menuRows = menuRowDao.findAll();
        return menuRowMapper.convertToMenuRowList(menuRows);
    }

    @Override
    @Transactional
    public MenuRowDTO update(MenuRowDTO menuRowDTO, Long id, LocalDateTime updateDate) {
        MenuRow updateRow = menuRowDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateRow.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("MenuRow has been already edited");
        }
        updateRow.setUpdateDate(LocalDateTime.now());
        updateRow.setPrice(menuRowDTO.getPrice());
        PizzaInfo pizzaInfo = pizzaInfoMapper.convertToPizzaInfo(menuRowDTO.getPizzaInfo());
        updateRow.setPizzaInfo(pizzaInfo);
        Menu menu = menuMapper.convertToMenu(menuRowDTO.getMenu());
        updateRow.setMenu(menu);
        updateRow = menuRowDao.save(updateRow);
        return menuRowMapper.convertToMenuRowDTO(updateRow);
    }

    @Override
    @Transactional
    public void delete(Long id, LocalDateTime updateDate) {
        MenuRow deleteRow = menuRowDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteRow.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("MenuRow has been already edited");
        }
        menuRowDao.deleteById(id);
    }
}