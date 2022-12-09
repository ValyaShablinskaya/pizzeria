package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuRowServiceTest {

    @Mock
    private MenuRowDao menuRowDao;
    @InjectMocks
    private MenuRowService menuRowService;

    @Test
    void addMenuRowShouldReturnCorrectResult() {
        PizzaInfo pizzaInfo = PizzaInfo.builder()
                .id(1L)
                .name("Margarita")
                .description("testy")
                .size(16L)
                .build();

        MenuRow menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .pizzaInfo(pizzaInfo)
                .build();

        when(menuRowDao.save(menuRow)).thenReturn(Optional.of(menuRow));

        menuRowService.add(menuRow);
        verify(menuRowDao).save(menuRow);
    }

    @Test
    void findMenuRowByIdShouldReturnCorrectResult() {
        PizzaInfo pizzaInfo = PizzaInfo.builder()
                .id(1L)
                .name("Margarita")
                .description("testy")
                .size(16L)
                .build();

        MenuRow menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .pizzaInfo(pizzaInfo)
                .build();

        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.of(menuRow));
        menuRowService.findById(menuRow.getId());
        verify(menuRowDao).findById(menuRow.getId());
    }

    @Test
    void findMenuRowByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        PizzaInfo pizzaInfo = PizzaInfo.builder()
                .id(1L)
                .name("Margarita")
                .description("testy")
                .size(16L)
                .build();

        MenuRow menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .pizzaInfo(pizzaInfo)
                .build();

        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> menuRowService.findById(menuRow.getId()));
        verifyNoMoreInteractions(menuRowDao);
    }

    @Test
    void findAllMenuRowsShouldReturnCorrectResult() {
        List<MenuRow> menuRows = new ArrayList<>();

        menuRows.add(MenuRow.builder()
                .id(1L)
                .price(21.2)
                .build());

        menuRows.add(MenuRow.builder()
                .id(2L)
                .price(50.5)
                .build());

        when(menuRowDao.findAll()).thenReturn(menuRows);
        menuRowService.findAll();
        verify(menuRowDao).findAll();
    }

    @Test
    void updateMenuRowShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        MenuRow menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .updateDate(updateData)
                .build();

        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.of(menuRow));
        when(menuRowDao.update(menuRow)).thenReturn(Optional.of(menuRow));
        menuRowService.update(menuRow, menuRow.getId(), updateData);
        verify(menuRowDao).update(menuRow);
    }

    @Test
    void removeMenuRowByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        MenuRow menuRow = MenuRow.builder()
                .id(1L)
                .price(21.2)
                .updateDate(updateData)
                .build();

        when(menuRowDao.findById(menuRow.getId())).thenReturn(Optional.of(menuRow));
        menuRowService.deleteById(menuRow.getId(), updateData);
        verify(menuRowDao).deleteById(menuRow.getId());
    }

    @Test
    void findAllMenuRowsByMenuIdShouldReturnListOfMenuRows() {
        List<MenuRow> menuRows = new ArrayList<>();

        menuRows.add(MenuRow.builder()
                .id(1L)
                .price(21.2)
                .build());

        menuRows.add(MenuRow.builder()
                .id(2L)
                .price(50.5)
                .build());

        when(menuRowDao.findAllRowsByIdMenu(1L)).thenReturn(menuRows);
        menuRowService.findAllByIdMenu(1L);
        verify(menuRowDao).findAllRowsByIdMenu(1L);
    }
}