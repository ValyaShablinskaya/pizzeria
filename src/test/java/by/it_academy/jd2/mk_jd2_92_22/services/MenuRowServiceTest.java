package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuRowService;
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
class MenuRowServiceTest {

    @Mock
    private IMenuRowDao menuRowDao;
    @Mock
    private IMenuRowMapper menuRowMapper;
    @Mock
    private IPizzaInfoMapper pizzaInfoMapper;
    @Mock
    private IMenuMapper menuMapper;
    @InjectMocks
    private MenuRowService menuRowService;
    private MenuRowDTO menuRowDTO;
    private MenuRow menuRow;
    private PizzaInfo pizzaInfo;
    private PizzaInfoDTO pizzaInfoDTO;
    private Menu menu;
    private MenuDTO menuDTO;
    private List<MenuRow> menuRows = new ArrayList<>();
    private List<MenuRowDTO> menuRowsDTO = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addMenuRowShouldReturnCorrectResult() {
        when(menuRowMapper.convertToMenuRow(menuRowDTO)).thenReturn(menuRow);
        when(menuRowDao.save(menuRow)).thenReturn(menuRow);
        when(menuRowMapper.convertToMenuRowDTO(menuRow)).thenReturn(menuRowDTO);

        assertEquals(menuRowDTO, menuRowService.create(menuRowDTO));

        verify(menuRowMapper).convertToMenuRow(menuRowDTO);
        verify(menuRowDao).save(menuRow);
        verify(menuRowMapper).convertToMenuRowDTO(menuRow);
    }

    @Test
    void findMenuRowByIdShouldReturnCorrectResult() {
        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.of(menuRow));
        when(menuRowMapper.convertToMenuRowDTO(menuRow)).thenReturn(menuRowDTO);

        assertEquals(menuRowDTO, menuRowService.read(menuRow.getId()));

        verify(menuRowDao).findById(menuRow.getId());
        verify(menuRowMapper).convertToMenuRowDTO(menuRow);
    }

    @Test
    void findMenuRowByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> menuRowService.read(menuRow.getId()));
        verifyNoMoreInteractions(menuRowDao);
    }

    @Test
    void findAllMenuRowsShouldReturnCorrectResult() {
        when(menuRowDao.findAll()).thenReturn(menuRows);
        when(menuRowMapper.convertToMenuRowList(menuRows)).thenReturn(menuRowsDTO);

        assertEquals(menuRowsDTO, menuRowService.get());

        verify(menuRowDao).findAll();
        verify(menuRowMapper).convertToMenuRowList(menuRows);
    }

    @Test
    void updateMenuRowShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.of(menuRow));
        when(pizzaInfoMapper.convertToPizzaInfo(menuRowDTO.getPizzaInfo())).thenReturn(pizzaInfo);
        when(menuMapper.convertToMenu(menuDTO)).thenReturn(menu);
        when(menuRowDao.save(menuRow)).thenReturn(menuRow);
        when(menuRowMapper.convertToMenuRowDTO(menuRow)).thenReturn(menuRowDTO);

        assertEquals(menuRowDTO, menuRowService.update(menuRowDTO, menuRow.getId(), updateData));

        verify(pizzaInfoMapper).convertToPizzaInfo(pizzaInfoDTO);
        verify(menuMapper).convertToMenu(menuDTO);
        verify(menuRowDao).save(menuRow);
        verify(menuRowMapper).convertToMenuRowDTO(menuRow);
    }

    @Test
    void removeMenuRowByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.of(menuRow));
        menuRowService.delete(menuRow.getId(), updateData);
        verify(menuRowDao).deleteById(menuRow.getId());
    }

    private void generateTestValues() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
        LocalDateTime createData =
                LocalDateTime.of(2022, Month.DECEMBER, 10, 10, 10, 10, 10);

        pizzaInfo = PizzaInfo.builder()
                .id(1L)
                .name("Margarita")
                .description("testy")
                .size(16L)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        pizzaInfoDTO = PizzaInfoDTO.builder()
                .name("Margarita")
                .description("testy")
                .size(16L)
                .build();

        menu= Menu.builder()
                .id(1L)
                .name("Autumn")
                .enabled(true)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        menuDTO= MenuDTO.builder()
                .name("Autumn")
                .enabled(true)
                .build();

        menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .pizzaInfo(pizzaInfo)
                .creationDate(createData)
                .updateDate(updateData)
                .menu(menu)
                .build();

        menuRowDTO = MenuRowDTO.builder()
                .price(21.2)
                .pizzaInfo(pizzaInfoDTO)
                .menu(menuDTO)
                .build();

        menuRows.add(menuRow);
        menuRowsDTO.add(menuRowDTO);
    }
}