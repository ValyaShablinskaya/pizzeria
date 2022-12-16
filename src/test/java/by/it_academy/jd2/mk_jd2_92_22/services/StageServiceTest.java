package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IStageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IOrderStatusMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IStageMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.StageService;
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
public class StageServiceTest {
    @Mock
    private IStageDao stageDao;
    @Mock
    private IStageMapper stageMapper;
    @Mock
    private IOrderStatusMapper orderStatusMapper;
    @InjectMocks
    private StageService stageService;
    private Stage stage;
    private StageDTO stageDTO;
    private OrderStatus orderStatus;
    private OrderStatusDTO orderStatusDTO;
    private Order order;
    private OrderDTO orderDTO;
    private List<Stage> stages = new ArrayList<>();
    private List<StageDTO> stageDTOList = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addStageShouldReturnCorrectResult() {
        when(stageMapper.convertToStage(stageDTO)).thenReturn(stage);
        when(stageDao.save(stage)).thenReturn(stage);
        when(stageMapper.convertToStageDTO(stage)).thenReturn(stageDTO);

        assertEquals(stageDTO, stageService.create(stageDTO));

        verify(stageMapper).convertToStage(stageDTO);
        verify(stageDao).save(stage);
        verify(stageMapper).convertToStageDTO(stage);
    }

    @Test
    void findStageByIdShouldReturnCorrectResult() {
        when(stageDao.findById(stage.getId())).thenReturn(Optional.of(stage));
        when(stageMapper.convertToStageDTO(stage)).thenReturn(stageDTO);

        assertEquals(stageDTO, stageService.read(stage.getId()));

        verify(stageDao).findById(stage.getId());
        verify(stageMapper).convertToStageDTO(stage);
    }

    @Test
    void findStageByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(stageDao.findById(stage.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> stageService.read(stage.getId()));
        verifyNoMoreInteractions(stageDao);
    }

    @Test
    void findAllStageShouldReturnCorrectResult() {
        when(stageDao.findAll()).thenReturn(stages);
        when(stageMapper.convertToStageList(stages)).thenReturn(stageDTOList);

        assertEquals(stageDTOList, stageService.get());

        verify(stageDao).findAll();
        verify(stageMapper).convertToStageList(stages);
    }

    @Test
    void updateStageShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(stageDao.findById(stage.getId())).thenReturn(Optional.of(stage));
        when(orderStatusMapper.convertToOrderStatus(stageDTO.getOrderStatus())).thenReturn(orderStatus);
        when(stageDao.save(stage)).thenReturn(stage);
        when(stageMapper.convertToStageDTO(stage)).thenReturn(stageDTO);

        assertEquals(stageDTO, stageService.update(stageDTO, stage.getId(), updateData));

        verify(orderStatusMapper).convertToOrderStatus(stageDTO.getOrderStatus());
        verify(stageDao).save(stage);
        verify(stageMapper).convertToStageDTO(stage);
    }

    @Test
    void removeStageByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(stageDao.findById(stage.getId())).thenReturn(Optional.of(stage));
        stageService.delete(stage.getId(), updateData);
        verify(stageDao).deleteById(stage.getId());
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

        Ticket ticket = Ticket.builder()
                .id(3L)
                .order(order)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        TicketDTO ticketDTO = TicketDTO.builder()
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

        stage = Stage.builder()
                .id(1L)
                .description("beginning")
                .orderStatus(orderStatus)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        stageDTO = StageDTO.builder()
                .description("beginning")
                .orderStatus(orderStatusDTO)
                .build();

        stages.add(stage);
        stageDTOList.add(stageDTO);
    }
}