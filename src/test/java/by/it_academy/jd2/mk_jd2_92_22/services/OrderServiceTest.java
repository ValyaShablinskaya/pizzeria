package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ISelectedItemMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.OrderService;
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
public class OrderServiceTest {
    @Mock
    private IOrderDao orderDao;
    @Mock
    private IOrderMapper orderMapper;
    @Mock
    private ISelectedItemMapper selectedItemMapper;
    @InjectMocks
    private OrderService orderService;
    private Order order;
    private OrderDTO orderDTO;
    private List<Order> orders = new ArrayList<>();
    private List<OrderDTO> orderDTOList = new ArrayList<>();
    private List<SelectedItem> selectedItemList = new ArrayList<>();
    private List<SelectedItemDTO> selectedItemDTOList = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addOrderShouldReturnCorrectResult() {
        when(orderMapper.convertToOrder(orderDTO)).thenReturn(order);
        when(orderDao.save(order)).thenReturn(order);
        when(orderMapper.convertToOrderDTO(order)).thenReturn(orderDTO);

        assertEquals(orderDTO, orderService.create(orderDTO));

        verify(orderMapper).convertToOrder(orderDTO);
        verify(orderDao).save(order);
        verify(orderMapper).convertToOrderDTO(order);
    }

    @Test
    void findOrderByIdShouldReturnCorrectResult() {
        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderMapper.convertToOrderDTO(order)).thenReturn(orderDTO);

        assertEquals(orderDTO, orderService.read(order.getId()));

        verify(orderDao).findById(order.getId());
        verify(orderMapper).convertToOrderDTO(order);
    }

    @Test
    void findOrderByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(orderDao.findById(order.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderService.read(order.getId()));
        verifyNoMoreInteractions(orderDao);
    }

    @Test
    void findAllOrdersShouldReturnCorrectResult() {
        when(orderDao.findAll()).thenReturn(orders);
        when(orderMapper.convertToOrderList(orders)).thenReturn(orderDTOList);

        assertEquals(orderDTOList, orderService.get());

        verify(orderDao).findAll();
        verify(orderMapper).convertToOrderList(orders);
    }

    @Test
    void updateOrderShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));
        when(selectedItemMapper.convertToSelectedItemListFromDto(orderDTO.getSelectedItems())).thenReturn(selectedItemList);
        when(orderDao.save(order)).thenReturn(order);
        when(orderMapper.convertToOrderDTO(order)).thenReturn(orderDTO);

        assertEquals(orderDTO, orderService.update(orderDTO, order.getId(), updateData));

        verify(selectedItemMapper).convertToSelectedItemListFromDto(orderDTO.getSelectedItems());
        verify(orderDao).save(order);
        verify(orderMapper).convertToOrderDTO(order);
    }

    @Test
    void removeMenuByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(orderDao.findById(order.getId())).thenReturn(Optional.of(order));
        orderService.delete(order.getId(), updateData);
        verify(orderDao).deleteById(order.getId());
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

        SelectedItem selectedItem = SelectedItem.builder()
                .id(1L)
                .count(25L)
                .menuRow(menuRow)
                .order(order)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        SelectedItemDTO selectedItemDTO = SelectedItemDTO.builder()
                .count(25L)
                .menuRow(menuRowDTO)
                .order(orderDTO)
                .build();

        selectedItemList.add(selectedItem);
        selectedItemDTOList.add(selectedItemDTO);

        order = Order.builder()
                .id(1L)
                .selectedItems(selectedItemList)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        orderDTO = OrderDTO.builder()
                .selectedItems(selectedItemDTOList)
                .build();

        orders.add(order);
        orderDTOList.add(orderDTO);
    }
}
