package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ISelectedItemDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IMenuRowMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ISelectedItemMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.SelectedItemService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.*;
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
public class SelectedItemServiceTest {
    @Mock
    private ISelectedItemDao selectedItemDao;
    @Mock
    private ISelectedItemMapper selectedItemMapper;
    @Mock
    private IMenuRowMapper menuRowMapper;
    @Mock
    private IOrderMapper orderMapper;
    @InjectMocks
    private SelectedItemService selectedItemService;
    private SelectedItem selectedItem;
    private SelectedItemDTO selectedItemDTO;
    private MenuRow menuRow;
    private MenuRowDTO menuRowDTO;
    private Order order;
    private OrderDTO orderDTO;
    private List<SelectedItem> selectedItems = new ArrayList<>();
    private List<SelectedItemDTO> selectedItemDTOList = new ArrayList<>();
    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addSelectedItemShouldReturnCorrectResult() {
        when(selectedItemMapper.convertToSelectedItem(selectedItemDTO)).thenReturn(selectedItem);
        when(selectedItemDao.save(selectedItem)).thenReturn(selectedItem);
        when(selectedItemMapper.convertToSelectedItemDTO(selectedItem)).thenReturn(selectedItemDTO);

        assertEquals(selectedItemDTO, selectedItemService.create(selectedItemDTO));

        verify(selectedItemMapper).convertToSelectedItem(selectedItemDTO);
        verify(selectedItemDao).save(selectedItem);
        verify(selectedItemMapper).convertToSelectedItemDTO(selectedItem);
    }

    @Test
    void findSelectedItemByIdShouldReturnCorrectResult() {
        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.of(selectedItem));
        when(selectedItemMapper.convertToSelectedItemDTO(selectedItem)).thenReturn(selectedItemDTO);

        assertEquals(selectedItemDTO, selectedItemService.read(selectedItem.getId()));

        verify(selectedItemDao).findById(selectedItem.getId());
        verify(selectedItemMapper).convertToSelectedItemDTO(selectedItem);
    }

    @Test
    void findSelectedItemByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> selectedItemService.read(selectedItem.getId()));
        verifyNoMoreInteractions(selectedItemDao);
    }

    @Test
    void findAllSelectedItemsShouldReturnCorrectResult() {
        when(selectedItemDao.findAll()).thenReturn(selectedItems);
        when(selectedItemMapper.convertToSelectedItemList(selectedItems)).thenReturn(selectedItemDTOList);

        assertEquals(selectedItemDTOList, selectedItemService.get());

        verify(selectedItemDao).findAll();
        verify(selectedItemMapper).convertToSelectedItemList(selectedItems);
    }

    @Test
    void updateSelectedItemShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.of(selectedItem));
        when(menuRowMapper.convertToMenuRow(selectedItemDTO.getMenuRow())).thenReturn(menuRow);
        when(orderMapper.convertToOrder(selectedItemDTO.getOrder())).thenReturn(order);
        when(selectedItemDao.save(selectedItem)).thenReturn(selectedItem);
        when(selectedItemMapper.convertToSelectedItemDTO(selectedItem)).thenReturn(selectedItemDTO);

        assertEquals(selectedItemDTO, selectedItemService.update(selectedItemDTO, selectedItem.getId(), updateData));

        verify(menuRowMapper).convertToMenuRow(selectedItemDTO.getMenuRow());
        verify(orderMapper).convertToOrder(selectedItemDTO.getOrder());
        verify(selectedItemDao).save(selectedItem);
        verify(selectedItemMapper).convertToSelectedItemDTO(selectedItem);
    }

    @Test
    void removeSelectedItemByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.of(selectedItem));
        selectedItemService.delete(selectedItem.getId(), updateData);
        verify(selectedItemDao).deleteById(selectedItem.getId());
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

        Menu menu= Menu.builder()
                .id(1L)
                .name("Autumn")
                .enabled(true)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        MenuDTO menuDTO= MenuDTO.builder()
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

        order = Order.builder()
                .id(1L)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        orderDTO = OrderDTO.builder()
                .build();

        selectedItem = SelectedItem.builder()
                .id(1L)
                .count(25L)
                .menuRow(menuRow)
                .order(order)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        selectedItemDTO = SelectedItemDTO.builder()
                .count(25L)
                .menuRow(menuRowDTO)
                .order(orderDTO)
                .build();

        selectedItems.add(selectedItem);
        selectedItemDTOList.add(selectedItemDTO);
    }
}
