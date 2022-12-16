package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IDoneOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IDoneOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.DoneOrderService;
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
public class DoneOrderServiceTest {
    @Mock
    private IDoneOrderDao doneOrderDao;
    @Mock
    private IDoneOrderMapper doneOrderMapper;
    @Mock
    private IPizzaMapper pizzaMapper;
    @Mock
    private ITicketMapper ticketMapper;
    @InjectMocks
    private DoneOrderService doneOrderService;
    private DoneOrder doneOrder;
    private DoneOrderDTO doneOrderDTO;
    private Pizza pizza;
    private PizzaDTO pizzaDTO;
    private Ticket ticket;
    private TicketDTO ticketDTO;
    private Order order;
    private OrderDTO orderDTO;
    private List<DoneOrder> doneOrders = new ArrayList<>();
    private List<DoneOrderDTO> doneOrderDTOList = new ArrayList<>();
    private List<Pizza> pizzas = new ArrayList<>();
    private List<PizzaDTO> pizzaDTOList = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addDoneOrderShouldReturnCorrectResult() {
        when(doneOrderMapper.convertToDoneOrder(doneOrderDTO)).thenReturn(doneOrder);
        when(doneOrderDao.save(doneOrder)).thenReturn(doneOrder);
        when(doneOrderMapper.convertToDoneOrderDTO(doneOrder)).thenReturn(doneOrderDTO);

        assertEquals(doneOrderDTO, doneOrderService.create(doneOrderDTO));

        verify(doneOrderMapper).convertToDoneOrder(doneOrderDTO);
        verify(doneOrderDao).save(doneOrder);
        verify(doneOrderMapper).convertToDoneOrderDTO(doneOrder);
    }

    @Test
    void findDoneOrderByIdShouldReturnCorrectResult() {
        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.of(doneOrder));
        when(doneOrderMapper.convertToDoneOrderDTO(doneOrder)).thenReturn(doneOrderDTO);

        assertEquals(doneOrderDTO, doneOrderService.read(doneOrder.getId()));

        verify(doneOrderDao).findById(doneOrder.getId());
        verify(doneOrderMapper).convertToDoneOrderDTO(doneOrder);
    }

    @Test
    void findDoneOrderByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> doneOrderService.read(doneOrder.getId()));
        verifyNoMoreInteractions(doneOrderDao);
    }

    @Test
    void findAllDoneOrdersShouldReturnCorrectResult() {
        when(doneOrderDao.findAll()).thenReturn(doneOrders);
        when(doneOrderMapper.convertToDoneOrderList(doneOrders)).thenReturn(doneOrderDTOList);

        assertEquals(doneOrderDTOList, doneOrderService.get());

        verify(doneOrderDao).findAll();
        verify(doneOrderMapper).convertToDoneOrderList(doneOrders);
    }

    @Test
    void updateDoneOrderShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.of(doneOrder));
        when(ticketMapper.convertToTicket(doneOrderDTO.getTicket())).thenReturn(ticket);
        when(pizzaMapper.convertToPizzaListFromDto(doneOrderDTO.getPizzas())).thenReturn(pizzas);
        when(doneOrderDao.save(doneOrder)).thenReturn(doneOrder);
        when(doneOrderMapper.convertToDoneOrderDTO(doneOrder)).thenReturn(doneOrderDTO);

        assertEquals(doneOrderDTO, doneOrderService.update(doneOrderDTO, doneOrder.getId(), updateData));

        verify(ticketMapper).convertToTicket(doneOrderDTO.getTicket());
        verify(pizzaMapper).convertToPizzaListFromDto(doneOrderDTO.getPizzas());
        verify(doneOrderDao).save(doneOrder);
        verify(doneOrderMapper).convertToDoneOrderDTO(doneOrder);
    }

    @Test
    void removeDoneOrderByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(doneOrderDao.findById(doneOrder.getId())).thenReturn(Optional.of(doneOrder));
        doneOrderService.delete(doneOrder.getId(), updateData);
        verify(doneOrderDao).deleteById(doneOrder.getId());
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

        List<SelectedItem> selectedItemList = new ArrayList<>();
        List<SelectedItemDTO> selectedItemDTOList = new ArrayList<>();
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

        ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        ticketDTO = TicketDTO.builder()
                .order(orderDTO)
                .build();

        pizza = Pizza.builder()
                .id(1L)
                .doneOrder(doneOrder)
                .name("Margarita")
                .size(32L)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        pizzaDTO = PizzaDTO.builder()
                .doneOrder(doneOrderDTO)
                .name("Margarita")
                .size(32L)
                .build();

        pizzas.add(pizza);
        pizzaDTOList.add(pizzaDTO);

        doneOrder = DoneOrder.builder()
                .id(1L)
                .pizzas(pizzas)
                .ticket(ticket)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        doneOrderDTO = DoneOrderDTO.builder()
                .pizzas(pizzaDTOList)
                .ticket(ticketDTO)
                .build();

        doneOrders.add(doneOrder);
        doneOrderDTOList.add(doneOrderDTO);
    }
}
