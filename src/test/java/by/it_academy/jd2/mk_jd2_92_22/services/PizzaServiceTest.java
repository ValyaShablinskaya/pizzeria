//package by.it_academy.jd2.mk_jd2_92_22.services;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.*;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaService;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PizzaServiceTest {
//    @Mock
//    private PizzaDao pizzaDao;
//    @InjectMocks
//    private PizzaService pizzaService;
//
//    @Test
//    void addPizzaShouldReturnCorrectResult() {
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        Ticket ticket = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        DoneOrder doneOrder = DoneOrder.builder()
//                .id(1L)
//                .ticket(ticket)
//                .build();
//
//        Pizza pizza = Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .build();
//
//        when(pizzaDao.save(pizza)).thenReturn(Optional.of(pizza));
//
//        pizzaService.add(pizza);
//        verify(pizzaDao).save(pizza);
//    }
//
//    @Test
//    void findPizzaByIdShouldReturnCorrectResult() {
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        Ticket ticket = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        DoneOrder doneOrder = DoneOrder.builder()
//                .id(1L)
//                .ticket(ticket)
//                .build();
//
//        Pizza pizza = Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .build();
//
//        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.of(pizza));
//        pizzaService.findById(pizza.getId());
//        verify(pizzaDao).findById(pizza.getId());
//    }
//
//    @Test
//    void findPizzaByIdShouldReturnExceptionWhenGetIncorrectParameters() {
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        Ticket ticket = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        DoneOrder doneOrder = DoneOrder.builder()
//                .id(1L)
//                .ticket(ticket)
//                .build();
//
//        Pizza pizza = Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .build();
//
//        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.empty());
//        assertThrows(EntityNotFoundException.class, () -> pizzaService.findById(pizza.getId()));
//        verifyNoMoreInteractions(pizzaDao);
//    }
//
//    @Test
//    void findAllPizzasShouldReturnCorrectResult() {
//        List<Pizza> pizzas = new ArrayList<>();
//
//        pizzas.add(Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .build());
//
//        pizzas.add(Pizza.builder()
//                .id(2L)
//                .name("Margarita")
//                .size(25L)
//                .build());
//
//        when(pizzaDao.findAll()).thenReturn(pizzas);
//        pizzaService.findAll();
//        verify(pizzaDao).findAll();
//    }
//
//    @Test
//    void updatePizzaShouldReturnCorrectResult() {
//        LocalDateTime updateData =
//                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        Ticket ticket = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        DoneOrder doneOrder = DoneOrder.builder()
//                .id(1L)
//                .ticket(ticket)
//                .build();
//
//        Pizza pizza = Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .updateDate(updateData)
//                .build();
//
//        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.of(pizza));
//        when(pizzaDao.update(pizza)).thenReturn(Optional.of(pizza));
//        pizzaService.update(pizza, pizza.getId(), updateData);
//        verify(pizzaDao).update(pizza);
//    }
//
//    @Test
//    void removePizzaByIdShouldReturnCorrectResult() {
//        LocalDateTime updateData =
//                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        Ticket ticket = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        DoneOrder doneOrder = DoneOrder.builder()
//                .id(1L)
//                .ticket(ticket)
//                .build();
//
//        Pizza pizza = Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .updateDate(updateData)
//                .build();
//
//        when(pizzaDao.findById(pizza.getId())).thenReturn(Optional.of(pizza));
//        pizzaService.deleteById(pizza.getId(), updateData);
//        verify(pizzaDao).deleteById(pizza.getId());
//    }
//
//    @Test
//    void findAllPizzasByDoneOrderIdShouldReturnListOfMenuRows() {
//        List<Pizza> pizzas = new ArrayList<>();
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        Ticket ticket = Ticket.builder()
//                .id(1L)
//                .order(order)
//                .build();
//
//        DoneOrder doneOrder = DoneOrder.builder()
//                .id(1L)
//                .ticket(ticket)
//                .build();
//
//        pizzas.add(Pizza.builder()
//                .id(1L)
//                .name("cheese")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .build());
//
//        pizzas.add(Pizza.builder()
//                .id(2L)
//                .name("Margarita")
//                .size(25L)
//                .doneOrder(doneOrder)
//                .build());
//
//        when(pizzaDao.findAllPizzasByIdDoneOrder(1L)).thenReturn(pizzas);
//        pizzaService.findAllByIdDoneOrder(1L);
//        verify(pizzaDao).findAllPizzasByIdDoneOrder(1L);
//    }
//}
