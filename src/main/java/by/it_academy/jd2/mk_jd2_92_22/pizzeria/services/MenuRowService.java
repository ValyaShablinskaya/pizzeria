package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuRowDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional(readOnly = true)
public class MenuRowService implements IMenuRowService {
    private final IMenuRowDao menuRowDao;
    private final IMenuDao menuDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "MenuRow is not found";

    public MenuRowService(IMenuRowDao menuRowDao, IMenuDao menuDao) {
        this.menuRowDao = menuRowDao;
        this.menuDao = menuDao;
    }

    @Override
    @Transactional
    public MenuRowDTO create(MenuRowDTO menuRowDTO) {
        MenuRow menuRow = IMenuRowMapper.INSTANCE.convertToMenuRow(menuRowDTO);
        menuRow.setCreationDate(LocalDateTime.now());
        menuRow.setUpdateDate(menuRow.getCreationDate());
        menuRow = menuRowDao.save(menuRow);
        return IMenuRowMapper.INSTANCE.convertToMenuRowDTO(menuRow);
    }

    @Override
    public MenuRowDTO read(Long id) {
        MenuRow menuRow = menuRowDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IMenuRowMapper.INSTANCE.convertToMenuRowDTO(menuRow);
    }

    @Override
    public List<MenuRowDTO> get() {
        List<MenuRow> menuRows = menuRowDao.findAll();
        return IMenuRowMapper.INSTANCE.convertToMenuRowList(menuRows);
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
        PizzaInfo pizzaInfo = IPizzaInfoMapper.INSTANCE.convertToPizzaInfo(menuRowDTO.getPizzaInfo());
        updateRow.setPizzaInfo(pizzaInfo);
        updateRow = menuRowDao.save(updateRow);
        return IMenuRowMapper.INSTANCE.convertToMenuRowDTO(updateRow);
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

    @Override
    public void addRowToMenu(Long menuId, Long menuRowId) {
        MenuRow menuRow = menuRowDao.findById(menuRowId).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        Menu menu = menuDao.findById(menuId).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        menuRow.addMenuToMenuRow(menu);
        menuRowDao.save(menuRow);
    }

//    @Override
//    public List<MenuRowDTO> findAllByIdMenu(Long id) {
//        List<MenuRow> menuRows = menuRowDao.findAllRowsByIdMenu(id);
//        return IMenuRowMapper.INSTANCE.convertToMenuRowList(menuRows);
//    }
}