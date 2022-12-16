package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ITicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.TicketService;
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
public class TicketServiceTest {
    @Mock
    private ITicketDao ticketDao;
    @Mock
    private ITicketMapper ticketMapper;
    @Mock
    private IOrderMapper orderMapper;
    @InjectMocks
    private TicketService ticketService;
    private Ticket ticket;
    private TicketDTO ticketDTO;
    private Order order;
    private OrderDTO orderDTO;
    private List<Ticket> tickets = new ArrayList<>();
    private List<TicketDTO> ticketDTOList = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addTicketShouldReturnCorrectResult() {
        when(ticketMapper.convertToTicket(ticketDTO)).thenReturn(ticket);
        when(ticketDao.save(ticket)).thenReturn(ticket);
        when(ticketMapper.convertToTicketDTO(ticket)).thenReturn(ticketDTO);

        assertEquals(ticketDTO, ticketService.create(ticketDTO));

        verify(ticketMapper).convertToTicket(ticketDTO);
        verify(ticketDao).save(ticket);
        verify(ticketMapper).convertToTicketDTO(ticket);
    }

    @Test
    void findTicketByIdShouldReturnCorrectResult() {
        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        when(ticketMapper.convertToTicketDTO(ticket)).thenReturn(ticketDTO);

        assertEquals(ticketDTO, ticketService.read(ticket.getId()));

        verify(ticketDao).findById(ticket.getId());
        verify(ticketMapper).convertToTicketDTO(ticket);
    }

    @Test
    void findTicketByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> ticketService.read(ticket.getId()));
        verifyNoMoreInteractions(ticketDao);
    }

    @Test
    void findAllTicketsShouldReturnCorrectResult() {
        when(ticketDao.findAll()).thenReturn(tickets);
        when(ticketMapper.convertToTicketList(tickets)).thenReturn(ticketDTOList);

        assertEquals(ticketDTOList, ticketService.get());

        verify(ticketDao).findAll();
        verify(ticketMapper).convertToTicketList(tickets);
    }

    @Test
    void updateTicketShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        when(orderMapper.convertToOrder(ticketDTO.getOrder())).thenReturn(order);
        when(ticketDao.save(ticket)).thenReturn(ticket);
        when(ticketMapper.convertToTicketDTO(ticket)).thenReturn(ticketDTO);

        assertEquals(ticketDTO, ticketService.update(ticketDTO, ticket.getId(), updateData));

        verify(orderMapper).convertToOrder(ticketDTO.getOrder());
        verify(ticketDao).save(ticket);
        verify(ticketMapper).convertToTicketDTO(ticket);
    }

    @Test
    void removeTicketByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(ticketDao.findById(ticket.getId())).thenReturn(Optional.of(ticket));
        ticketService.delete(ticket.getId(), updateData);
        verify(ticketDao).deleteById(ticket.getId());
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

        tickets.add(ticket);
        ticketDTOList.add(ticketDTO);
    }
}
