package by.it_academy.jd2.mk_jd2_92_22.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.*;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StageDaoTest {
    private final static String PROPERTIES = "src/test/resources/h2.properties";
    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";

    private BDConnector connector;
    private TableCreator tableCreator;
    private StageDao stageDao;
    private OrderStatusDao orderStatusDao;
    private Stage stageOne;
    private OrderDao orderDao;
    private TicketDao ticketDao;
    private Stage stageTwo;
    private List<Stage> stages = new ArrayList<>();

    @BeforeEach
    void prepareTable() {
        connector = new BDConnector(PROPERTIES);
        tableCreator = new TableCreator(connector);
        stageDao = new StageDao(connector);
        orderStatusDao = new OrderStatusDao(connector);
        orderDao = new OrderDao(connector);
        ticketDao = new TicketDao(connector);

        createTestData();
    }

    @Test
    void saveShouldAddStageToTheDatabase() {

        Stage stage = Stage.builder()
                .id(3L)
                .description("with pineapple")
                .updateDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        stageDao.save(stage);
        Stage actual = stageDao.findById(3L).get();

        assertEquals(stage, actual);
    }

    @Test
    void findByIdShouldReturnStageWhenGetId() {
        Stage actual = stageDao.findById(1L).get();
        assertEquals(stageOne, actual);
    }

    @Test
    void findAllShouldReturnListOfStage() {
        List<Stage> actual = stageDao.findAll();
        assertEquals(stages, actual);
    }

    @Test
    void updateShouldUpdateStage() {
        Stage expected = Stage.builder()
                .id(1L)
                .description("big")
                .build();

        stageDao.update(expected);
        Stage actual = stageDao.findById(1L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByIdShouldDeleteStageWhenGetId() {
        stageDao.deleteById(2L);
        Stage actual = stageDao.findById(2L).orElse(null);
        assertThat(actual).isNull();
    }

    @Test
    void findAllStagesByOrderStatusIdShouldReturnListOfMenuRows() {
        List<Stage> expected = new ArrayList<>();

        expected.add(Stage.builder()
                .id(1L)
                .description("testy")
                .build());

        List <Stage> actual = stageDao.findAllStageByIdOrderStatus(1L);
        assertEquals(expected, actual);
    }

    private void createTestData() {
        tableCreator.runScript(SCRIPT_SQL);

        stageOne = Stage.builder()
                .id(1L)
                .description("testy")
                .build();

        stageTwo = Stage.builder()
                .id(2L)
                .description("very testy")
                .build();

        Order order = Order.builder()
                .id(1L)
                .build();
        orderDao.save(order);

       Ticket ticket = Ticket.builder()
                .id(1L)
                .order(order)
                .build();
       ticketDao.save(ticket);

        OrderStatus orderStatus = OrderStatus.builder()
                .id(1L)
                .ticket(ticket)
                .isDone(true)
                .build();

        orderStatusDao.save(orderStatus);

        stageDao.save(stageOne);
        stageDao.save(stageTwo);
        stages.add(stageOne);
        stages.add(stageTwo);

        stageDao.addStageOnOrderStatus(1L, 1L);
    }
}
