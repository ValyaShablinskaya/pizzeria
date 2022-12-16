package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderStatusMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IStageMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.ITicketMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.OrderStatusService;
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
public class OrderStatusServiceTest {
    @Mock
    private IOrderStatusDao orderStatusDao;
    @Mock
    private IOrderStatusMapper orderStatusMapper;
    @Mock
    private ITicketMapper ticketMapper;
    @Mock
    private IStageMapper stageMapper;
    @InjectMocks
    private OrderStatusService orderStatusService;
    private OrderStatus orderStatus;
    private OrderStatusDTO orderStatusDTO;
    private Ticket ticket;
    private TicketDTO ticketDTO;
    private Stage stage;
    private StageDTO stageDTO;
    private Order order;
    private OrderDTO orderDTO;
    private List<OrderStatus> orderStatuses = new ArrayList<>();
    private List<OrderStatusDTO> orderStatusDTOList = new ArrayList<>();
    private List<Stage> stages = new ArrayList<>();
    private List<StageDTO> stageDTOList = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addSelectedItemShouldReturnCorrectResult() {
        when(orderStatusMapper.convertToOrderStatus(orderStatusDTO)).thenReturn(orderStatus);
        when(orderStatusDao.save(orderStatus)).thenReturn(orderStatus);
        when(orderStatusMapper.convertToOrderStatusDTO(orderStatus)).thenReturn(orderStatusDTO);

        assertEquals(orderStatusDTO, orderStatusService.create(orderStatusDTO));

        verify(orderStatusMapper).convertToOrderStatus(orderStatusDTO);
        verify(orderStatusDao).save(orderStatus);
        verify(orderStatusMapper).convertToOrderStatusDTO(orderStatus);
    }

    @Test
    void findSelectedItemByIdShouldReturnCorrectResult() {
        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.of(orderStatus));
        when(orderStatusMapper.convertToOrderStatusDTO(orderStatus)).thenReturn(orderStatusDTO);

        assertEquals(orderStatusDTO, orderStatusService.read(orderStatus.getId()));

        verify(orderStatusDao).findById(orderStatus.getId());
        verify(orderStatusMapper).convertToOrderStatusDTO(orderStatus);
    }

    @Test
    void findSelectedItemByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> orderStatusService.read(orderStatus.getId()));
        verifyNoMoreInteractions(orderStatusDao);
    }

    @Test
    void findAllSelectedItemsShouldReturnCorrectResult() {
        when(orderStatusDao.findAll()).thenReturn(orderStatuses);
        when(orderStatusMapper.convertToOrderStatusList(orderStatuses)).thenReturn(orderStatusDTOList);

        assertEquals(orderStatusDTOList, orderStatusService.get());

        verify(orderStatusDao).findAll();
        verify(orderStatusMapper).convertToOrderStatusList(orderStatuses);
    }

    @Test
    void updateSelectedItemShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.of(orderStatus));
        when(ticketMapper.convertToTicket(orderStatusDTO.getTicket())).thenReturn(ticket);
        when(stageMapper.convertToStageListFromDto(orderStatusDTO.getHistory())).thenReturn(stages);
        when(orderStatusDao.save(orderStatus)).thenReturn(orderStatus);
        when(orderStatusMapper.convertToOrderStatusDTO(orderStatus)).thenReturn(orderStatusDTO);

        assertEquals(orderStatusDTO, orderStatusService.update(orderStatusDTO, orderStatus.getId(), updateData));

        verify(ticketMapper).convertToTicket(orderStatusDTO.getTicket());
        verify(stageMapper).convertToStageListFromDto(orderStatusDTO.getHistory());
        verify(orderStatusDao).save(orderStatus);
        verify(orderStatusMapper).convertToOrderStatusDTO(orderStatus);
    }

    @Test
    void removeSelectedItemByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(orderStatusDao.findById(orderStatus.getId())).thenReturn(Optional.of(orderStatus));
        orderStatusService.delete(orderStatus.getId(), updateData);
        verify(orderStatusDao).deleteById(orderStatus.getId());
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

        stage = Stage.builder()
                .id(1L)
                .orderStatus(orderStatus)
                .description("done")
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        stageDTO = StageDTO.builder()
                .orderStatus(orderStatusDTO)
                .description("done")
                .build();

        orderStatus = OrderStatus.builder()
                .id(3L)
                .ticket(ticket)
                .isDone(true)
                .history(stages)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        orderStatusDTO = OrderStatusDTO.builder()
                .ticket(ticketDTO)
                .isDone(true)
                .history(stageDTOList)
                .build();

        orderStatuses.add(orderStatus);
        orderStatusDTOList.add(orderStatusDTO);
    }
}
