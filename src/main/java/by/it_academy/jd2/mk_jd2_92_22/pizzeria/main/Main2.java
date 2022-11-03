package by.it_academy.jd2.mk_jd2_92_22.pizzeria.main;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaInfoService;
import org.codehaus.jackson.map.ObjectMapper;

import java.time.LocalDateTime;

public class Main2 {
    public static void main(String[] args) {
        String BDPROPERTY = "/BDProperty.properties";

        BDConnector bdConnector = new BDConnector(BDPROPERTY);
        MenuRowDao dao = new MenuRowDao(bdConnector);
        MenuRowService service = new MenuRowService(dao, new MenuDao(bdConnector));
        PizzaInfoDao pizzaInfoDao = new PizzaInfoDao(bdConnector);
        PizzaInfoService pizzaInfoService = new PizzaInfoService(pizzaInfoDao);
        MenuDao menuDao = new MenuDao(bdConnector);
        MenuService menuService = new MenuService(menuDao);
        ObjectMapper mapper = new ObjectMapper();
        Converter converter = new Converter();

//        service.add(MenuRow.builder()
//                .pizzaInfo(pizzaInfoService.findById(3L))
//                .price(2.5)
//                .creationDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build());
//        PizzaInfo pizzaInfo = pizzaInfoService.findById(4L);
//        System.out.println(pizzaInfo);
//        PizzaInfo pizzaInfo2 = PizzaInfo.builder()
//                .name("cvb")
//                .description("lll")
//                .size(25L)
//                .creationDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build();
//        pizzaInfoService.add(pizzaInfo2);
//
//        MenuRow menu = dao.findById(1l).get();
//        System.out.println(menu);
//        System.out.println(service.findById(1L));
//      System.out.println(pizzaInfoService.findById(3L));
//        System.out.println(service.findAll());      service.add(MenuRow.builder()
//                .pizzaInfo(pizzaInfoService.findById(3L))
//                .price(2.5)
//                .creationDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build());
//
//
//
//        System.out.println(menu);
//        System.out.println(service.findById(1L));
//      System.out.println(pizzaInfoService.findById(3L));
//        System.out.println(service.findAll());
//        service.update(4L, MenuRow.builder()
//                .price(50.6)
//                .build());
//        menuService.add(Menu.builder()
//                .creationDate(LocalDateTime.now())
//                .updateDate(LocalDateTime.now())
//                .build());
//        System.out.println(menuService.findById(2L));

//        System.out.println(service.findAllByIdMenu(2L));
        //service.addRowToMenu(2L, 10L);
       // System.out.println(service.findById(2L));
//        menuService.update(Menu.builder()
//                .id(2L)
//                .creationDate(LocalDateTime.now())
//                .build());
       // menuService.deleteById(5L);
   }
}
