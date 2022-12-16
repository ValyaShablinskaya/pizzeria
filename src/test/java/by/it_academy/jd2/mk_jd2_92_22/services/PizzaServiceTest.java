package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IPizzaDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IDoneOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaService;
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
public class PizzaServiceTest {
    @Mock
    private IPizzaDao pizzaDao;
    @Mock
    private IPizzaMapper pizzaMapper;
    @Mock
    private IDoneOrderMapper doneOrderMapper;
    @InjectMocks
    private PizzaService pizzaService;

    private Pizza pizza;
    private PizzaDTO pizzaDTO;
    private DoneOrder doneOrder;
    private DoneOrderDTO doneOrderDTO;
    private Order order;
    private OrderDTO orderDTO;
    private Ticket ticket;
    private TicketDTO ticketDTO;
    private List<Pizza> pizzas = new ArrayList<>();
    private List<PizzaDTO> pizzaDTOList = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addPizzaShouldReturnCorrectResult() {
        when(pizzaMapper.convertToPizza(pizzaDTO)).thenReturn(pizza);
        when(pizzaDao.save(pizza)).thenReturn(pizza);
        when(pizzaMapper.convertToPizzaDTO(pizza)).thenReturn(pizzaDTO);

        assertEquals(pizzaDTO, pizzaService.create(pizzaDTO));

        verify(pizzaMapper).convertToPizza(pizzaDTO);
        verify(pizzaDao).save(pizza);
        verify(pizzaMapper).convertToPizzaDTO(pizza);
    }

    @Test
    void findPizzaByIdShouldReturnCorrectResult() {
        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.of(pizza));
        when(pizzaMapper.convertToPizzaDTO(pizza)).thenReturn(pizzaDTO);

        assertEquals(pizzaDTO, pizzaService.read(pizza.getId()));

        verify(pizzaDao).findById(pizza.getId());
        verify(pizzaMapper).convertToPizzaDTO(pizza);
    }

    @Test
    void findPizzaByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> pizzaService.read(pizza.getId()));
        verifyNoMoreInteractions(pizzaDao);
    }

    @Test
    void findAllPizzasShouldReturnCorrectResult() {
        when(pizzaDao.findAll()).thenReturn(pizzas);
        when(pizzaMapper.convertToPizzaList(pizzas)).thenReturn(pizzaDTOList);

        assertEquals(pizzaDTOList, pizzaService.get());

        verify(pizzaDao).findAll();
        verify(pizzaMapper).convertToPizzaList(pizzas);
    }

    @Test
    void updatePizzaShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.of(pizza));
        when(doneOrderMapper.convertToDoneOrder(pizzaDTO.getDoneOrder())).thenReturn(doneOrder);
        when(pizzaDao.save(pizza)).thenReturn(pizza);
        when(pizzaMapper.convertToPizzaDTO(pizza)).thenReturn(pizzaDTO);

        assertEquals(pizzaDTO, pizzaService.update(pizzaDTO, pizza.getId(), updateData));

        verify(doneOrderMapper).convertToDoneOrder(doneOrderDTO);
        verify(pizzaDao).save(pizza);
        verify(pizzaMapper).convertToPizzaDTO(pizza);
    }

    @Test
    void removePizzaByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.of(pizza));
        pizzaService.delete(pizza.getId(), updateData);
        verify(pizzaDao).deleteById(pizza.getId());
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

        pizza = Pizza.builder()
                .id(1L)
                .name("cheese")
                .size(25L)
                .doneOrder(doneOrder)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        pizzaDTO = PizzaDTO.builder()
                .name("cheese")
                .size(25L)
                .doneOrder(doneOrderDTO)
                .build();

        pizzas.add(pizza);
        pizzaDTOList.add(pizzaDTO);
    }
}
