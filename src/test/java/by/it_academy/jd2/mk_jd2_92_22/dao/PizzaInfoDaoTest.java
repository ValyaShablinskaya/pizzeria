//package by.it_academy.jd2.mk_jd2_92_22.dao;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TableCreator;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//class PizzaInfoDaoTest {
//    private final static String PROPERTIES = "src/test/resources/h2.properties";
//    private final static String SCRIPT_SQL = "src/test/resources/schema.sql";
//
//    private BDConnector connector;
//    private TableCreator tableCreator;
//    private PizzaInfoDao pizzaInfoDao;
//    private PizzaInfo pizzaInfo1;
//    private PizzaInfo pizzaInfo2;
//    private List<PizzaInfo> pizzaInfos = new ArrayList<>();
//
//    @BeforeEach
//    void prepareTable() {
//        connector = new BDConnector(PROPERTIES);
//        tableCreator = new TableCreator(connector);
//        pizzaInfoDao = new PizzaInfoDao(connector);
//
//        createTestData();
//    }
//
//    @Test
//    void saveShouldAddPizzaInfoToTheDatabase() {
//
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(3L)
//                .name("Hawaiian")
//                .description("with pineapple")
//                .size(32L)
//                .updateDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build();
//
//        pizzaInfoDao.save(pizzaInfo);
//        PizzaInfo actual = pizzaInfoDao.findById(3L).get();
//
//        assertEquals(pizzaInfo, actual);
//    }
//
//    @Test
//    void findByIdShouldReturnPizzaInfoWhenGetId() {
//        PizzaInfo actual = pizzaInfoDao.findById(1L).get();
//        assertEquals(pizzaInfo1, actual);
//    }
//
//    @Test
//    void findAllShouldReturnListOfPizzaInfo() {
//        List<PizzaInfo> actual = pizzaInfoDao.findAll();
//        assertEquals(pizzaInfos, actual);
//    }
//
//    @Test
//    void updateShouldUpdatePizzaInfo() {
//        PizzaInfo expected = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        pizzaInfoDao.update(expected);
//        PizzaInfo actual = pizzaInfoDao.findById(1L).orElse(null);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteByIdShouldDeletePizzaInfoWhenGetId() {
//        pizzaInfoDao.deleteById(2L);
//        PizzaInfo actual = pizzaInfoDao.findById(2L).orElse(null);
//        assertThat(actual).isNull();
//    }
//
//    private void createTestData() {
//        tableCreator.runScript(SCRIPT_SQL);
//
//       pizzaInfo1 = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(32L)
//                .build();
//
//        pizzaInfo2 = PizzaInfo.builder()
//                .id(2L)
//                .name("4season")
//                .description("very testy")
//                .size(32L)
//                .build();
//
//        pizzaInfoDao.save(pizzaInfo1);
//        pizzaInfoDao.save(pizzaInfo2);
//        pizzaInfos.add(pizzaInfo1);
//        pizzaInfos.add(pizzaInfo2);
//    }
//}
