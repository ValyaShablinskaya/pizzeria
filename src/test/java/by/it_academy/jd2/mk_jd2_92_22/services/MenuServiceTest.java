package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.MenuRowDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @Mock
    private IMenuDao menuDao;
    @Mock
    private IMenuMapper menuMapper;
    @Mock
    private IMenuRowMapper menuRowMapper;
    @InjectMocks
    private MenuService menuService;
    private Menu menu;
    private MenuDTO menuDTO;

    private List<Menu> menus = new ArrayList<>();
    private List<MenuDTO> menuDTOList = new ArrayList<>();
    private List<MenuRow> menuRows = new ArrayList<>();
    private List<MenuRowDTO> menuRowsDTO = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addMenuShouldReturnCorrectResult() {
        when(menuMapper.convertToMenu(menuDTO)).thenReturn(menu);
        when(menuDao.save(menu)).thenReturn(menu);
        when(menuMapper.convertToMenuDTO(menu)).thenReturn(menuDTO);

        assertEquals(menuDTO, menuService.create(menuDTO));

        verify(menuMapper).convertToMenu(menuDTO);
        verify(menuDao).save(menu);
        verify(menuMapper).convertToMenuDTO(menu);
    }

    @Test
    void findMenuByIdShouldReturnCorrectResult() {
        when(menuDao.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(menuMapper.convertToMenuDTO(menu)).thenReturn(menuDTO);

        assertEquals(menuDTO, menuService.read(menu.getId()));

        verify(menuDao).findById(menu.getId());
        verify(menuMapper).convertToMenuDTO(menu);
    }

    @Test
    void findMenuByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(menuDao.findById(menu.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> menuService.read(menu.getId()));
        verifyNoMoreInteractions(menuDao);
    }

    @Test
    void findAllMenusShouldReturnCorrectResult() {
        when(menuDao.findAll()).thenReturn(menus);
        when(menuMapper.convertToMenuList(menus)).thenReturn(menuDTOList);

        assertEquals(menuDTOList, menuService.get());

        verify(menuDao).findAll();
        verify(menuMapper).convertToMenuList(menus);
    }

    @Test
    void updateMenuShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(menuDao.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(menuRowMapper.convertToMenuRowListFromDto(menuDTO.getMenuRows())).thenReturn(menuRows);
        when(menuDao.save(menu)).thenReturn(menu);
        when(menuMapper.convertToMenuDTO(menu)).thenReturn(menuDTO);

        assertEquals(menuDTO, menuService.update(menuDTO, menu.getId(), updateData));

        verify(menuRowMapper).convertToMenuRowListFromDto(menuDTO.getMenuRows());
        verify(menuDao).save(menu);
        verify(menuMapper).convertToMenuDTO(menu);
    }

    @Test
    void removeMenuByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(menuDao.findById(menu.getId())).thenReturn(Optional.of(menu));
        menuService.delete(menu.getId(), updateData);
        verify(menuDao).deleteById(menu.getId());
    }

    private void generateTestValues() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
        LocalDateTime createData =
                LocalDateTime.of(2022, Month.DECEMBER, 10, 10, 10, 10, 10);

        PizzaInfo pizzaInfo = PizzaInfo.builder()
                .id(1L)
                .name("Margarita")
                .description("testy")
                .size(16L)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        PizzaInfoDTO pizzaInfoDTO = PizzaInfoDTO.builder()
                .name("Margarita")
                .description("testy")
                .size(16L)
                .build();

        menuDTO = MenuDTO.builder()
                .name("Autumn")
                .enabled(true)
                .build();

        menu = Menu.builder()
                .id(1L)
                .name("Autumn")
                .enabled(true)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        menus.add(menu);
        menuDTOList.add(menuDTO);

        menus.add(Menu.builder()
                .id(2L)
                .name("Weekend")
                .enabled(true)
                .creationDate(createData)
                .updateDate(updateData)
                .build());

        menuDTOList.add(MenuDTO.builder()
                .name("Weekend")
                .enabled(true)
                .build());

        MenuRow menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .pizzaInfo(pizzaInfo)
                .creationDate(createData)
                .updateDate(updateData)
                .menu(menu)
                .build();

        MenuRowDTO menuRowDTO = MenuRowDTO.builder()
                .price(21.2)
                .pizzaInfo(pizzaInfoDTO)
                .menu(menuDTO)
                .build();

        menuRows.add(menuRow);
        menuRowsDTO.add(menuRowDTO);
    }
}
