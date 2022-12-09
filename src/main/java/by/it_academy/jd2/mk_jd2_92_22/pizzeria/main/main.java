//package by.it_academy.jd2.mk_jd2_92_22.pizzeria.main;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IMenuService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.time.LocalDateTime;
//
//
//public class main {
//    public static void main(String[] args) {
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("pizza.xml");
//
////        IPizzaInfoService service = context.getBean(IPizzaInfoService.class);
////
////        service.add(PizzaInfo.builder()
////                .name("testy")
////                .size(25L)
////                .build());
////        List<PizzaInfo> pizzaInfos = service.findAll();
////
////        for (PizzaInfo pizzaInfo : pizzaInfos) {
////            System.out.println(pizzaInfo);
////        }
////        IMenuService service = context.getBean(IMenuService.class);
////        Menu menu = Menu.builder()
////                .name("New")
////                .enabled(true)
////                .build();
////        System.out.println(menu);
////        LocalDateTime date = service.findById(12L).getUpdateDate();
////
////        service.update(menu, 12L, date);
////    }
////
////}
