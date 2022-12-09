package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuRowDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class MenuRowService implements IMenuRowService {
    private final MenuRowDao menuRowDao;
    private final MenuDao menuDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "MenuRow is not found";

    public MenuRowService(MenuRowDao menuRowDao, MenuDao menuDao) {
        this.menuRowDao = menuRowDao;
        this.menuDao = menuDao;
    }

    @Override
    public MenuRowDTO add(MenuRowDTO menuRowDTO) {
        MenuRow menuRow = IMenuRowMapper.INSTANCE.convertToMenuRow(menuRowDTO);
        menuRow.setCreationDate(LocalDateTime.now());
        menuRow.setUpdateDate(menuRow.getCreationDate());
        menuRow = menuRowDao.save(menuRow).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuRowMapper.INSTANCE.convertToMenuRowDTO(menuRow);
    }

    @Override
    public MenuRowDTO findById(Long id) {
        MenuRow menuRow = menuRowDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuRowMapper.INSTANCE.convertToMenuRowDTO(menuRow);
    }

    @Override
    public List<MenuRowDTO> findAll() {
        List<MenuRow> menuRows = menuRowDao.findAll();
        return IMenuRowMapper.INSTANCE.convertToMenuRowList(menuRows);
    }

    @Override
    public MenuRowDTO update(MenuRowDTO menuRowDTO, Long id, LocalDateTime updateDate) {
        MenuRow updateRow = menuRowDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updateRow.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("MenuRow has been already edited");
        }
        updateRow.setUpdateDate(LocalDateTime.now());
        updateRow.setPrice(menuRowDTO.getPrice());
        PizzaInfo pizzaInfo = IPizzaInfoMapper.INSTANCE.convertToPizzaInfo(menuRowDTO.getPizzaInfo());
        updateRow.setPizzaInfo(pizzaInfo);
        updateRow = menuRowDao.update(updateRow).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuRowMapper.INSTANCE.convertToMenuRowDTO(updateRow);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        MenuRow deleteRow = menuRowDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deleteRow.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("MenuRow has been already edited");
        }
        menuRowDao.deleteById(id);
    }

    @Override
    public void addRowToMenu(Long menuId, Long menuRowId) {
        if (!menuRowDao.findById(menuRowId).isPresent()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION);
        }
        if (!menuDao.findById(menuId).isPresent()) {
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION);
        }
        menuRowDao.addRowOnMenu(menuId, menuRowId);
    }

    @Override
    public List<MenuRowDTO> findAllByIdMenu(Long id) {
        List<MenuRow> menuRows = menuRowDao.findAllRowsByIdMenu(id);
        return IMenuRowMapper.INSTANCE.convertToMenuRowList(menuRows);
    }
}